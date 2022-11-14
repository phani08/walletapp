package com.wallet.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletDaoImpl implements WalletDao {

	
	Map<Integer, Wallet> wallets = new HashMap<>();
@Override
	public Wallet addWallet(Wallet newWallet) throws WalletException {
		
		this.wallets.put(newWallet.getId(), newWallet);
		
		return this.wallets.get(newWallet.getId());
		
	}

	@Override
	public Wallet getWalletById(Integer walletId) throws WalletException {
		
		System.out.println();
		return this.wallets.get(walletId);
		
	}

	@Override
	public Wallet updateWallet(Wallet updateWallet) throws WalletException
	{
		//System.out.println(this.wallets.get(updateWallet);
		//System.out.println(this.wallets.get(updateWallet));
		this.wallets.replace(updateWallet.getId(), updateWallet);
		return this.wallets.get(updateWallet.getId());
	}

	@Override
	public Wallet deleteWalletById(Integer walletID) throws WalletException  {
		if (!this.wallets.containsKey(walletID))
			throw new WalletException("Id does not exists to Delete");
		return this.wallets.remove(walletID);
		//return null;
	}

	
	
}