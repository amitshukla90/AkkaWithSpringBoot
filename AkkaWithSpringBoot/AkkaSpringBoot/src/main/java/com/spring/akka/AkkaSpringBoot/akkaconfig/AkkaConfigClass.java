package com.spring.akka.AkkaSpringBoot.akkaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import akka.actor.ActorSystem;

@Component
public class AkkaConfigClass {
	
	@Bean
	@Autowired
	public ActorSystem getActorSystem() {
		return ActorSystem.create("MyFirstProgramAkka");
	}

}
