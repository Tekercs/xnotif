#include <iostream>
#include "xnoteserv_networking_serversocket.h"

using namespace xnoteserv::networking;

int main()
{
	std::cout << "hello world!!" << std::endl;

	ServerSocket* ssocket = new ServerSocket;
	ssocket->start();

	return 0;
}