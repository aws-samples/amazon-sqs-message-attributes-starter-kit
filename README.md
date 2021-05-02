# Amazon SQS - Message Attributes in Action

This is a companion source code for **Amazon Compute Blog - Enhancing message-based applications with Message Attributes**. The samples in this project demonstrate how to use Message Attributes feature of Amazon Simple Queue Service (SQS).

## Pre-requisites

 1. JDK 8 or above
 1. IDE for e.g. [Eclipse](https://www.eclipse.org/) or [Spring Tools](https://spring.io/tools) or [Intellij IDEA](https://www.jetbrains.com/idea/)
 1. [Apache Maven](https://maven.apache.org/)
 1. Access to AWS Environment

## AWS Service Requirements

1. 1 Amazon SQS Queue of type Standard
1. 1 Amazon SQS Queue of type FIFO

## Build Instructions

 1. Clone this starter kit to your Laptop / MacBook
 1. It has Maven nature, so you can import it to your IDE
 1. Create an SQS queue of type Standard. Make a note of its URL
 1. Create an SQS queue of type FIFO. Make a note of its URL

## Functional Overview

| Class                         | Purpose|
|----------------------------------| -------------- |
| [SendMessageWithAttributes.java](./src/main/java/software/amazon/sqs/sample/SendMessageWithAttributes.java) | Send message attributes to a standard queue. |
| [SendMessageWithAttributes_FIFO.java](./src/main/java/software/amazon/sqs/sample/SendMessageWithAttributes_FIFO.java) | Send message attributes to a FIFO queue. |
| [SendMessageWithAttributes_AWSXRay_Tracing.java](./src/main/java/software/amazon/sqs/sample/SendMessageWithAttributes_AWSXRay_Tracing.java) | Send message attributes to a standard queue and with AWS X-Ray Tracing enabled. |
| [SendMessageWithAttributes_AWSXRay_Tracing_FIFO.java](./src/main/java/software/amazon/sqs/sample/SendMessageWithAttributes_AWSXRay_Tracing_FIFO.java) | Send message attributes to a FIFO queue and with AWS X-Ray Tracing enabled. |
| [ReceiveMessage.java](./src/main/java/software/amazon/sqs/sample/ReceiveMessage.java) | Receive message from a standard queue. |
| [ReceiveMessage_FIFO.java](./src/main/java/software/amazon/sqs/sample/ReceiveMessage_FIFO.java) | Receive message from a FIFO queue. |
| [ReceiveMessage_AWSXRay_Tracing.java](./src/main/java/software/amazon/sqs/sample/ReceiveMessage_AWSXRay_Tracing.java) | Receive message from a standard queue and print AWSTraceHeader. |

## Test Instructions

1. Update Java classes with appropriate SQS queue URL
1. Run the programs in your IDE

## Tags

Amazon SQS, Message Attributes

## License Summary

This sample code is made available under the MIT license. See the LICENSE file.
