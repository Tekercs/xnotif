#include <xnotif/network/identitybroadcast.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <stdlib.h>
#include <thread>

#define BUFFER_SIZE 1

xnotif::network::IdentityBroadcast::IdentityBroadcast()
{
	this->stopThread = false;

	this->setupSocket();
}

void xnotif::network::IdentityBroadcast::setupSocket()
{
	this->sock = socket(PF_INET, SOCK_DGRAM, 0);	
	this->address.sin_family = AF_INET;
	this->address.sin_port = htons(14568);
	this->address.sin_addr.s_addr = inet_addr("0");
	//this->address.sin_addr.s_addr = INADDR_ANY;

	char broadcast = 'a';
	setsockopt(this->sock, SOL_SOCKET, SO_BROADCAST, &broadcast, sizeof(broadcast));

	memset(this->address.sin_zero, '\0', sizeof this->address.sin_zero);  

	bind(this->sock, (struct sockaddr *) &this->address, sizeof(this->address));
}

void xnotif::network::IdentityBroadcast::start()
{
	this->broadcastThread = new std::thread([&]()  
	{
		char buffer[BUFFER_SIZE];
		int messageSize;
		struct sockaddr_storage serverStorage;
		socklen_t addressSize;		

		addressSize = sizeof serverStorage;

		while (!this->stopThread)	
		{
			messageSize = recvfrom(this->sock,buffer,BUFFER_SIZE,0,(struct sockaddr *)&serverStorage, &addressSize); 

			std::cout << "incoming" << std::endl;
			sendto(this->sock, " ", 1, 0,(struct sockaddr *)&serverStorage, addressSize);	
		}	
	
	});			
}

void xnotif::network::IdentityBroadcast::stop()
{
	this->stopThread = true;
	this->broadcastThread->join();
	
	delete this->broadcastThread;
}
