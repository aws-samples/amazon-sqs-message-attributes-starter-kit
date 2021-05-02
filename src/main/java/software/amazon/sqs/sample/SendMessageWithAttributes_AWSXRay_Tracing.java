// Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazon.sqs.sample;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.interceptors.TracingInterceptor;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class SendMessageWithAttributes_AWSXRay_Tracing {

	public static void main(String[] args) {

		AWSXRay.beginSegment("Sqs-2");

		// TODO: update the value of queueUrl with the URL of standard queue you create
		// in your AWS account
		String queueUrl = "https://sqs.us-east-1.amazonaws.com/1234567890/sqs_queue_demo";
		SqsClient sqsClient = SqsClient.builder()
				.overrideConfiguration(
						ClientOverrideConfiguration.builder().addExecutionInterceptor(new TracingInterceptor()).build())
				.build();
		Map<String, MessageAttributeValue> messageAttributes = new HashMap<String, MessageAttributeValue>();
		messageAttributes.put("message_attribute_string_type",
				MessageAttributeValue.builder().dataType("String").stringValue("string_value").build());
		messageAttributes.put("message_attribute_number_type",
				MessageAttributeValue.builder().dataType("Number").stringValue("123").build());
		SdkBytes binaryValue = SdkBytes.fromString("my_binary_value", Charset.defaultCharset());
		messageAttributes.put("message_attribute_binary_type",
				MessageAttributeValue.builder().dataType("Binary").binaryValue(binaryValue).build());

		SendMessageResponse sendMessageResponse = null;

		for (int i = 10; i < 20; i++) {
			sendMessageResponse = sqsClient.sendMessage(SendMessageRequest.builder().queueUrl(queueUrl)
					.messageBody("SQS Message - " + i).messageAttributes(messageAttributes).delaySeconds(1).build());
			System.out.println("Message Id: " + sendMessageResponse.messageId());
		}

		AWSXRay.endSegment();
	}

}
