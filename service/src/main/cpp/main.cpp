#include <iostream>
#include "xnotif_network_serversocket.h"

using namespace xnotif::network;

int main()
{
	ServerSocket* ssocket = new ServerSocket;
	ssocket->bindSocket().lookForConnection();

	return 0;
}