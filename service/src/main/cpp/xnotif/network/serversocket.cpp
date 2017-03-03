#include <iostream>
#include <unistd.h>
#include <sys/socket.h>
#include <string.h>
#include <xnotif/network/serversocket.h>

xnotif::network::ServerSocket::ServerSocket()
{
	this->socketDesc = socket(AF_INET, SOCK_STREAM, 0);
	bzero((char *) &this->address, sizeof(this->address));
	this->address.sin_family = AF_INET;
    this->address.sin_port = htons((uint16_t) xnotif::network::ServerSocket::PORT_NUMBER);
    this->address.sin_addr.s_addr = INADDR_ANY;
}

xnotif::network::ServerSocket::~ServerSocket()
{
	this->close();
}

xnotif::network::ServerSocket& xnotif::network::ServerSocket::bindSocket()
{
	bind(this->socketDesc, (struct sockaddr *) &this->address, sizeof(this->address));
	return *this;
}

xnotif::network::Connection* xnotif::network::ServerSocket::lookForConnection()
{
	listen(this->socketDesc, 5);

	struct sockaddr_in clientAddress;
	int clientLength = sizeof(clientAddress);
	int clientSocket = accept(this->socketDesc, (struct sockaddr *) &clientAddress, (socklen_t *) &clientLength );

	return new Connection(clientSocket);
}

void xnotif::network::ServerSocket::close()
{
	shutdown(this->socketDesc, 2);
}
