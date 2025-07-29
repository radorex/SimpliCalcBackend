package com.calc.Calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calc.Calculator.bean.NumToSave;
import com.calc.Calculator.bean.num;
import com.calc.Calculator.entities.MemSave;
import com.calc.Calculator.service.Calculate;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:5501")
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
		
		@RequestMapping(value = "/ms",method = RequestMethod.POST,produces = "application/json")
		public ResponseEntity<String> memorySave(@RequestBody(required = true) NumToSave numS) throws Exception {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Content-Type","application/json");
			responseHeaders.set("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<String>(""+calc.memorySave(numS), responseHeaders, HttpStatus.OK);
			//return ""+calc.memorySave(num1);
		}
		
		@RequestMapping(value = "/mr",method = RequestMethod.GET,produces = "application/json")
		public ResponseEntity<MemSave> memoryRecall(@RequestParam(required = true) String userName) throws Exception {
			HttpHeaders responseHeaders = new HttpHeaders();
			//responseHeaders.setLocation(location);
			responseHeaders.set("SavedVal", calc.memCall(userName).getMemSaveVal());
			responseHeaders.set("Content-Type","application/json");
			responseHeaders.set("Access-Control-Allow-Origin", "http://127.0.0.1:5501");
			return new ResponseEntity<MemSave>(calc.memCall(userName), responseHeaders, HttpStatus.OK);
			//return ""+calc.memCall();
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> handleException(Exception ex){
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
		
}
