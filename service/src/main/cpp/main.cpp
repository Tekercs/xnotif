#include <iostream>
#include <xnotif/network/serversocket.h>
#include <xnotif/network/identitybroadcast.h>
#include <xnotif/network/connection.h>

using namespace xnotif::network;

int main()
{
	IdentityBroadcast* broadcast = new IdentityBroadcast("120", 125);
	broadcast->start();

	ServerSocket* ssocket = new ServerSocket;
	Connection* wildConnection = ssocket->bindSocket().lookForConnection();

	wildConnection->listenIncoming();

	while (true) {}

	wildConnection->close();
	ssocket->close();

	return 0;
}
