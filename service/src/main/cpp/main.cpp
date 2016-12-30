#include <iostream>
#include "xnotif_network_serversocket.h"
#include "xnotif_network_connection.h"

using namespace xnotif::network;

int main()
{
	ServerSocket* ssocket = new ServerSocket;
	Connection* wildConnection = ssocket->bindSocket().lookForConnection();
	wildConnection->listenIncoming();

	while (true) {}

	wildConnection->close();
	ssocket->close();

	return 0;
}