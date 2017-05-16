#ifndef XNOTIF_NETWORK_CONNECTION_H
#define XNOTIF_NETWORK_CONNECTION_H

#include <thread>

namespace xnotif::network
{
	class Connection
	{
	private:
		int clientDesc;
		std::thread* messageListener = nullptr;
		bool stopListener;

	public:
		Connection(int clientDesc);
		~Connection();
		void listenIncoming();
		void close();
	};
}

#endif