#include <xnotif_network_connection.h>
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
		int bytesRead;
		do
		{	
			std::cout << this->clientDesc << std::endl;

			char* tempContainer = new char[2000];
			bytesRead = recv(this->clientDesc, tempContainer, 2000, 0);

		  	if (bytesRead != 0)
		  	{
		  		std::cout << "message arrived: " << std::endl;
		  		std::string incomingMessage(tempContainer);
		  		std::cout << incomingMessage << std::endl;
		  	}

		}while (!this->stopListener && bytesRead != 0);

		std::cout << "closed lel" << std::endl;
	});
}
