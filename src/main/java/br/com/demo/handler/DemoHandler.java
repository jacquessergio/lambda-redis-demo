package br.com.demo.handler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DemoHandler implements RequestHandler<Map<String,String>, String> {

	@Override
	public String handleRequest(Map<String, String> input, Context context) {

		return "Hello Word";
	}


}
