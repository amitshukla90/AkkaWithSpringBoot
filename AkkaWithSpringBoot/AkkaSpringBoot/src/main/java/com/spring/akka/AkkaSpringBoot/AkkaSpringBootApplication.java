package com.spring.akka.AkkaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AkkaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkaSpringBootApplication.class, args);

		//final ActorSystem system = ActorSystem.create("MyFirstProgramAkka");

		/*final ActorRef printerActor = system.actorOf(Printer.prop(), "printerActor");
		final ActorRef greetHiActor = system.actorOf(Greeter.props("Hi ", printerActor), "greetHiActor");
		final ActorRef greetHeyActor = system.actorOf(Greeter.props("Hey", printerActor), "greetHeyActor");
		final ActorRef greetHelloActor = system.actorOf(Greeter.props("Hello ", printerActor), "greetHelloActor");

		greetHiActor.tell(new WhotoGreet("Akka"), ActorRef.noSender());
		greetHiActor.tell(new Greet(), ActorRef.noSender());
		
		greetHeyActor.tell(new WhotoGreet("Java"), ActorRef.noSender());
		greetHeyActor.tell(new Greet(), ActorRef.noSender());
		
		
		greetHelloActor.tell(new WhotoGreet("Web Services"), ActorRef.noSender());
		greetHelloActor.tell(new Greet(), ActorRef.noSender());*/
		
		
		
/*		final ActorRef restClientActor = system.actorOf(CallRestWebServices.props(),"restClientActor");
		final ActorRef getBlockForStore5505 = system.actorOf(CollectRestMessage.Props(restClientActor),"getBlockForStore5505");
		final ActorRef getBlockForStore5554 = system.actorOf(CollectRestMessage.Props(restClientActor),"getBlockForStore5554");
		final ActorRef getBlockForStore50001 = system.actorOf(CollectRestMessage.Props(restClientActor),"getBlockForStore50001");
		
		getBlockForStore5505.tell(new SetPayload("5505", "2018-10-20T08:00:00.000Z", "2018-10-22T18:00:00.000Z"), ActorRef.noSender());
		getBlockForStore5505.tell(new CallRestActor(),ActorRef.noSender());
		
		getBlockForStore5554.tell(new SetPayload("5554", "2018-10-20T08:00:00.000Z", "2018-10-22T18:00:00.000Z"), ActorRef.noSender());
		getBlockForStore5554.tell(new CallRestActor(),ActorRef.noSender());
		
		
		getBlockForStore50001.tell(new SetPayload("50001", "2018-10-20T08:00:00.000Z", "2018-10-22T18:00:00.000Z"), ActorRef.noSender());
		getBlockForStore50001.tell(new CallRestActor(),ActorRef.noSender());*/
	}
}
