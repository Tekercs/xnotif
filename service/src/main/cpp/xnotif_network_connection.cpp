#include "xnotif_network_connection.h"
#include <unistd.h>
#include <sys/socket.h>

xnotif::network::Connection::Connection(int clientDesc): clientDesc(clientDesc)
{

}

xnotif::network::Connection::~Connection()
{
	this->close();
}

void xnotif::network::Connection::close()
{
	shutdown(this->clientDesc, 2);
}