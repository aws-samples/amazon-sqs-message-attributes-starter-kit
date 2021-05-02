// Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazon.sqs.sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.MessageSystemAttributeName;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

public class ReceiveMessage {

	public static void main(String[] args) {

		// TODO: update the value of queueUrl with the URL of standard queue you create
		// in your AWS account
		String queueUrl = "https://sqs.us-east-1.amazonaws.com/1234567890/sqs_queue_demo";
		SqsClient sqsClient = SqsClient.builder().build();

		try {
			Collection<QueueAttributeName> attributeNames = new ArrayList<QueueAttributeName>();
			attributeNames.add(QueueAttributeName.ALL);

			Collection<String> messageAttributeNames = new ArrayList<String>();
			messageAttributeNames.add("All");
			messageAttributeNames.add(QueueAttributeName.ALL.toString());
			messageAttributeNames.add("message_attribute_string_type");
			messageAttributeNames.add("message_attribute_number_type");
			messageAttributeNames.add("message_attribute_binary_type");

			ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().queueUrl(queueUrl)
					.maxNumberOfMessages(10).attributeNames(attributeNames).messageAttributeNames(messageAttributeNames)
					.build();
			List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

			System.out.println("Size: " + messages.size());

			for (Message message : messages) {

				System.out.println();
				System.out.println("Message body: " + message.body());

				System.out.println("Message has system attributes: " + message.hasAttributes());
				System.out.println("Message has user attributes: " + message.hasMessageAttributes());

				System.out.println("\nFetchig Message System Attributes");
				// Fetch Message System Attributes
				Map<MessageSystemAttributeName, String> attributes = message.attributes();
				for (Map.Entry<MessageSystemAttributeName, String> entry : attributes.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}

				System.out.println("\nFetchig Message Attributes");
				// Fetch Message Attributes
				Map<String, MessageAttributeValue> messageAttributes = message.messageAttributes();
				for (Map.Entry<String, MessageAttributeValue> entry : messageAttributes.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
			}

		} catch (SqsException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}

	}

}
