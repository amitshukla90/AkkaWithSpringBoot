package com.spring.akka.AkkaSpringBoot.RestController;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.akka.AkkaSpringBoot.RestExample.CallRestWebServices;
import com.spring.akka.AkkaSpringBoot.RestExample.CollectRestMessage;
import com.spring.akka.AkkaSpringBoot.RestExample.CollectRestMessage.SetPayload;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.AskableActorRef;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

@RestController
public class AkkaRestController {

	final ActorSystem actorSystem = ActorSystem.create("MyFirstProgramAkka");
	final ActorRef restClientActor = actorSystem.actorOf(CallRestWebServices.props(), "restClientActor");
	final ActorRef getBlockForStore = actorSystem
			.actorOf(CollectRestMessage.Props(new AskableActorRef(restClientActor)), "getBlockForStore");
	final AskableActorRef restClientActorAskableRef = new AskableActorRef(restClientActor);
	final AskableActorRef getBlockForStoreActorAskableRef = new AskableActorRef(getBlockForStore);

	Timeout timeout = new Timeout(new FiniteDuration(15, TimeUnit.SECONDS));
	@GetMapping("/callAkka/{param}")
	public String runAkkaMethod(@PathVariable("param") String param) throws Exception {
		System.out.println("Param is ::" + param);
		Future<Object> future = getBlockForStoreActorAskableRef
				.ask(new SetPayload(param, "2018-07-20T08:00:00.000Z", "2018-11-22T18:00:00.000Z"),
						timeout);
		String response = (String) Await.result(future, timeout.duration());
		//System.out.println("Future is ::"+ future.toString());		

		return response;
	}

}
