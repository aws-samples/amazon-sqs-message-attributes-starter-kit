// Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazon.sqs.sample;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.xray.AWSXRay;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class SendMessageWithAttributes_FIFO {

	public static void main(String[] args) {
		// TODO: update the value of queueUrl with the URL of standard queue you create
		// in your AWS account
		String queueUrl = "https://sqs.us-east-1.amazonaws.com/1234567890/sqs_queue_demo_fifo.fifo";
		AWSXRay.beginSegment("Sqs-FIFO");
		SqsClient sqsClient = SqsClient.builder().build();
		Map<String, MessageAttributeValue> messageAttributes = new HashMap<String, MessageAttributeValue>();
		messageAttributes.put("message_attribute_string_type",
				MessageAttributeValue.builder().dataType("String").stringValue("string_value").build());
		messageAttributes.put("message_attribute_number_type",
				MessageAttributeValue.builder().dataType("Number").stringValue("123").build());
		SdkBytes binaryValue = SdkBytes.fromString("my_binary_value", Charset.defaultCharset());
		messageAttributes.put("message_attribute_binary_type",
				MessageAttributeValue.builder().dataType("Binary").binaryValue(binaryValue).build());

		SendMessageResponse sendMessageResponse = null;
		for (int i = 1000; i < 1010; i++) {
			sendMessageResponse = sqsClient
					.sendMessage(SendMessageRequest.builder().queueUrl(queueUrl).messageBody("SQS Message - " + i)
							.messageGroupId("Group-A").messageAttributes(messageAttributes).build());
			System.out.println("Message Id: " + sendMessageResponse.messageId());
		}
		AWSXRay.endSegment();
	}
}
