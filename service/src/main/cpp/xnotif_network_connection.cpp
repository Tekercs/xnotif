#include "xnotif_network_connection.h"
#include <unistd.h>

xnotif::network::Connection::Connection(int clientDesc): clientDesc(clientDesc)
{

}

xnotif::network::Connection::~Connection()
{
	this->closeConnection();
}

void xnotif::network::Connection::closeConnection()
{
	close(this->clientDesc);
}