#include <xnotif/network/connection.h>
#include <unistd.h>
#include <sys/socket.h>
#include <iostream>
#include <libnotify/notify.h>

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
		  	    /*
		  		std::cout << "message arrived: " << std::endl;
		  		std::string incomingMessage(tempContainer);
		  		std::cout << incomingMessage << std::endl;
		  		*/

		  		notify_init("Sample");
                NotifyNotification* n = notify_notification_new ("Hello world",
                                             "some message text... bla bla",
                                              0);
                notify_notification_set_timeout(n, 10000); // 10 seconds

                if (!notify_notification_show(n, 0))
                    std::cerr << "show has failed" << std::endl;
		  	}

		}while (!this->stopListener && bytesRead != 0);

		std::cout << "closed lel" << std::endl;
	});
}
