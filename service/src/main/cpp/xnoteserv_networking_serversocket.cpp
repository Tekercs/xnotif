#include <iostream>
#include <sys/socket.h>
#include <string.h>
#include "xnoteserv_networking_serversocket.h"

xnoteserv::networking::ServerSocket::ServerSocket()
{
	this->socketDesc = socket(AF_INET, SOCK_STREAM, 0);
	bzero((char *) &this->address, sizeof(this->address));
	this->address.sin_family = AF_INET;
    this->address.sin_port = htons((uint16_t) xnoteserv::networking::ServerSocket::PORT_NUMBER);
    this->address.sin_addr.s_addr = INADDR_ANY;
}

void xnoteserv::networking::ServerSocket::start()
{
	std::cout << "bindig" << std::endl;
	bind(this->socketDesc, (struct sockaddr *) &this->address, sizeof(this->address));

	std::cout << "Start listening ..." << std::endl;
	listen(this->socketDesc, 5);

	struct sockaddr_in clientAddress;
	int clientLength = sizeof(clientAddress);
	int clientSocket = accept(this->socketDesc, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLength );
}