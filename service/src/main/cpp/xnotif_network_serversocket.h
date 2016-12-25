#ifndef XNOTESERV_NETWORKING_SERVERSOCKET_H
#define XNOTESERV_NETWORKING_SERVERSOCKET_H

#include <cstdint>
#include <netinet/in.h>
#include "xnotif_network_connection.h"

namespace xnotif::network
{
	class ServerSocket
	{
	private:
		int socketDesc;
		struct sockaddr_in address;

	public:
		static const uint16_t PORT_NUMBER = 14567;

		ServerSocket();
		~ServerSocket();

		ServerSocket& bindSocket();
		Connection* lookForConnection();
		void closeSocket();
	};
}

#endif