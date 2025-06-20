package com.calc.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calc.Calculator.bean.num;
import com.calc.Calculator.service.Calculate;

@RestController
public class CalculatorController {
	
	@Autowired
	Calculate calc;

	//@GetMapping("/home")
		@RequestMapping(value = "/home",method = RequestMethod.GET,produces = "application/json")
		public String hello() {
			return "Hello, Welcome to Spring Boot.";
		}
		
		@RequestMapping(value = "/add",method = RequestMethod.GET,produces = "application/json")
		public float add(@RequestParam(required = true) float num1, @RequestParam(required = true) float num2) {
			return calc.add(new num(num1,num2));
		}
		
		@RequestMapping(value = "/subs",method = RequestMethod.GET,produces = "application/json")
		public float subs(@RequestParam(required = true) float num1, @RequestParam(required = true) float num2) {
			return calc.subs(new num(num1,num2));
		}
		
		@RequestMapping(value = "/multiply",method = RequestMethod.GET,produces = "application/json")
		public float multiply(@RequestParam(required = true) float num1, @RequestParam(required = true) float num2) {
			return calc.multiply(new num(num1,num2));
		}
		
		@RequestMapping(value = "/division",method = RequestMethod.GET,produces = "application/json")
		public float division(@RequestParam(required = true) float num1, @RequestParam(required = true) float num2) {
			return calc.division(new num(num1,num2));
		}
		
		@RequestMapping(value = "/ans",method = RequestMethod.GET,produces = "application/json")
		public float getAns() {
			return calc.getAns();
		}
		
		@RequestMapping(value = "/ms",method = RequestMethod.GET,produces = "application/json")
		public float memorySave(@RequestParam(required = true) float num1) throws Exception {
			return calc.memorySave(num1);
		}
		
		@RequestMapping(value = "/mr",method = RequestMethod.GET,produces = "application/json")
		public float memoryRecall() {
			return calc.memCall();
		}
}
