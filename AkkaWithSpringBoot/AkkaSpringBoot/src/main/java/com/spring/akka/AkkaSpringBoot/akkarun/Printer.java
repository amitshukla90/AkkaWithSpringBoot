package com.spring.akka.AkkaSpringBoot.akkarun;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Printer extends AbstractActor{
 static public Props prop() {
	 return Props.create(Printer.class, () -> new Printer());
 }
	
//#printer-messages

 private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

 public Printer() {
 }
 
//#printer-messages
 static public class Greeting {
   public final String message;

   public Greeting(String message) {
     this.message = message;
   }
 }

 @Override
 public Receive createReceive() {
   return receiveBuilder()
       .match(Greeting.class, greeting -> {
           log.info(greeting.message);
       })
       .build();
 }
//#pri

}
