// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.tribefire.cartridge.activemq.integration.test;

import com.braintribe.codec.marshaller.api.Marshaller;
import com.braintribe.codec.marshaller.bin.Bin2Marshaller;
import com.braintribe.codec.marshaller.bin.BinMarshaller;
import com.braintribe.codec.marshaller.common.BasicConfigurableMarshallerRegistry;
import com.braintribe.model.messaging.jms.JmsActiveMqConnection;
import com.braintribe.model.messaging.jms.JmsConnection;
import com.braintribe.transport.messaging.api.MessagingContext;
import com.braintribe.transport.messaging.jms.JmsActiveMqMessagingSessionProvider;

public class MessagingSessionTools {

	public static JmsActiveMqMessagingSessionProvider messagingSessionProvider() {
		JmsActiveMqMessagingSessionProvider bean = new JmsActiveMqMessagingSessionProvider();
		bean.setMessagingContext(messagingContext());
		bean.setConnectionDenotation(connectionDenotation());
		return bean;
	}

	private static JmsConnection connectionDenotation() {
		JmsActiveMqConnection bean = JmsActiveMqConnection.T.create("48194a50-6ca2-43a1-a948-4f6a56f35cb6");
		bean.setHostAddress("tcp://localhost:" + ActiveMqIntegrationTest.ACTIVEMQ_PORT + "?soTimeout=5000&daemon=true");
		return bean;
	}

	private static MessagingContext messagingContext() {
		MessagingContext bean = new MessagingContext();
		bean.setMarshaller(messageMarshaller());
		return bean;
	}

	private static Marshaller messageMarshaller() {
		Bin2Marshaller bean = new Bin2Marshaller();
		return bean;
	}

	private static BasicConfigurableMarshallerRegistry marshallerRegistry() {
		BasicConfigurableMarshallerRegistry bean = new BasicConfigurableMarshallerRegistry();
		bean.registerMarshaller("application/gm", binMarshaller());
		bean.registerMarshaller("gm/bin", binMarshaller());
		return bean;
	}

	private static BinMarshaller binMarshaller() {
		BinMarshaller bean = new BinMarshaller();
		bean.setWriteRequiredTypes(false);
		return bean;
	}

}
