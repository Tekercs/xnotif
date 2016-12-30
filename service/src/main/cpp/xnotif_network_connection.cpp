#include "xnotif_network_connection.h"
#include <unistd.h>
#include <sys/socket.h>
#include <iostream>

xnotif::network::Connection::Connection(int clientDesc): clientDesc(clientDesc)
{
	this->stopListener = false;
}

xnotif::network::Connection::~Connection()
{
	this->close();
}

void xnotif::network::Connection::close()
{
	this->stopListener = true;
	this->messageListener->join();
	delete this->messageListener;

	shutdown(this->clientDesc, 2);
}

void xnotif::network::Connection::listenIncoming()
{
	this->messageListener = new std::thread([&]() 
	{
		while (!this->stopListener)
		{
			std::cout << "Listening !!" << std::endl;
		}
	});
}