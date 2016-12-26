#ifndef XNOTIF_NETWORK_CONNECTION_H
#define XNOTIF_NETWORK_CONNECTION_H

namespace xnotif::network
{
	class Connection
	{
	private:
		int clientDesc;

	public:
		Connection(int clientDesc);
		~Connection();
		void closeConnection();
		
	};
}

#endif