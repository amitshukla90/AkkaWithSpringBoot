package com.spring.akka.AkkaSpringBoot.RestExample;

import java.util.concurrent.TimeUnit;

import com.spring.akka.AkkaSpringBoot.RestExample.CallRestWebServices.ReceivePayload;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.pattern.AskableActorRef;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

public class CollectRestMessage extends AbstractActor{
	
	public final AskableActorRef restActorRef;
	public  String payload ="";
	public String response = "";
	Timeout timeout = new Timeout(new FiniteDuration(15, TimeUnit.SECONDS));

	public CollectRestMessage(AskableActorRef restClientActor) {
		// TODO Auto-generated constructor stub
		this.restActorRef = restClientActor;
	}

	static public class SetPayload{
		public String storeId;
		private String startTime;
		private String endTime;
		
		public SetPayload(String storeId, String startTime, String endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.storeId = storeId;
		}

		@Override
		public String toString() {
			String val = "{\"payload\":{\"storeId\":\""+storeId+"\","
					+ "\"startDate\":\""+startTime+"\","
					+ "\"endDate\":\""+endTime+"\"}}";
			return val;
		}

/*		@Override
		public String toString() {
			String info = String.format("storeId = %s, startDate = %s, endDate = %s", storeId, startTime, endTime);
			return info;
			//return "payload {storeId:" + storeId + ", startDate:" + startTime + ", endDate:" + endTime + "}";
		}*/
		
		
		
		
	}
	
	static public class CallRestActor{
		public CallRestActor() {
			
		}
	}

	static public Props Props(AskableActorRef restClientActor) {
		return Props.create(CollectRestMessage.class,()->new CollectRestMessage(restClientActor));
	}
	
	
	
	@Override
	public Receive createReceive() {

		return receiveBuilder()
		        .match(SetPayload.class, setPayload -> {
		          this.payload = setPayload.toString();
		          Future<Object> future = restActorRef.ask(new ReceivePayload(payload),timeout, getSelf());
		           response = (String) Await.result(future, timeout.duration());
		           getSender().tell(response, getSelf());
		        })
		        .build();
	}

}
