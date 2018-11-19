package com.spring.akka.AkkaSpringBoot.RestExample;

import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.util.Timeout;
import scala.concurrent.duration.FiniteDuration;

public class CallRestWebServices extends AbstractActor{
	static RestTemplate restTemplate = new RestTemplate();
	static String URL = "http://sst-tomcat.sst-dev.sst.lms.glb.prod.walmart.com/sst-app/sst/blockbystore";
	static String URL_1 = "http://sst-tomcat.sst-dev.sst.lms.glb.prod.walmart.com/sst-app/sst/healthcheck";
	ObjectMapper mapper = new ObjectMapper();

	Timeout timeout = new Timeout(new FiniteDuration(15, TimeUnit.SECONDS));
	
	static public Props props() {
		return Props.create(CallRestWebServices.class,()->new CallRestWebServices());
	}

	public CallRestWebServices() {
		
	}
	 private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
	 
	static public class ReceivePayload{
		public final String payloadData;
		public ReceivePayload(String payloadData) {
			this.payloadData = payloadData;
		}
	}
	@Override
	public Receive createReceive() {
		return receiveBuilder()
			       .match(ReceivePayload.class, payload -> {
			    	   HttpHeaders headers = new HttpHeaders();
			    	   headers.setContentType(MediaType.APPLICATION_JSON);
			    	   HttpEntity<String> request = new HttpEntity<String>(payload.payloadData,headers);
			    	   String fut = restTemplate.postForObject(URL, request,String.class);
			    	  // log.info("Response is ::"+ fut);
			    	   getSender().tell(fut, getSelf());
			       })
			       .build();
	}

}
