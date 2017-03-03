#ifndef XNOTE_NETWORK_IDENTITYBROADCAST_H
#define XNOTE_NETWORK_IDENTITYBROADCAST_H

#include <netinet/in.h>
#include <iostream>
#include <string.h>
#include <thread>

namespace xnotif::network
{
		
	class IdentityBroadcast
	{
	private:
		int sock;
		struct sockaddr_in address;
		std::thread* broadcastThread;
		bool stopThread;

	public:
		IdentityBroadcast();
		void start();
		void stop();

	private:
		void setupSocket();
	};

}


#endif
