package com.spring.akka.AkkaSpringBoot.akkarun;

import com.spring.akka.AkkaSpringBoot.akkarun.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class Greeter extends AbstractActor{

	 //#greeter-messages

	  private final String message;
	  private final ActorRef printerActor;
	  private String greeting = "";
	  
	public Greeter(String message, ActorRef printerActor) {
		this.message = message;
		this.printerActor = printerActor;
	}

	static public class WhotoGreet {
		public final String who;

	    public WhotoGreet(String who) {
	        this.who = who;
	    }
	}
	
	  static public class Greet {
		    public Greet() {
		    }
		  }
	  
	  
	static public Props props(String message, ActorRef printerActor) {
		return Props.create(Greeter.class,() -> new Greeter(message, printerActor));
	}
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
		        .match(WhotoGreet.class, wtg -> {
		          this.greeting = message + ", " + wtg.who;
		        })
		        .match(Greet.class, x -> {
		          //#greeter-send-message
		          printerActor.tell(new Greeting(greeting), getSelf());
		          //#greeter-send-message
		        })
		        .build();
	}
	

}
