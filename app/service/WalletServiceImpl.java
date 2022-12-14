package com.wallet.app.service;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletServiceImpl implements WalletService{

	private WalletDao walletRepository = new WalletDaoImpl();

	@Override
	public Wallet registerWallet(Wallet newWallet)throws WalletException {
		
		return this.walletRepository.addWallet(newWallet);
		//return null;
	}

	@Override
	public Boolean login(Integer walletId, String password)throws WalletException
	{
		
		boolean isLogged = false;
		Wallet wallet = this.walletRepository.getWalletById(walletId);
		
				if(wallet.getPassword().equals(password))
				{
					
					 isLogged = true;
				}
				
				return isLogged;
	}

	@Override
	public Double addFundsToWallet(Integer walletId, Double amount)throws WalletException 
	{
		Wallet we = this.walletRepository.getWalletById(walletId);
		
		Double balance = we.getBalance();
		/* 
		 * balance = balance+amount;
		 */
		
		we.setBalance(we.getBalance()+amount);
		
		balance= this.walletRepository.updateWallet(we).getBalance();
		
		return balance;
		/*
		 * System.out.println(we);
		 * 
		 * return we.getBalance();
		 */
		
		
	}

	@Override
	public Double showWalletBalance(Integer walletId)throws WalletException {
		Wallet we=this.walletRepository.getWalletById(walletId);
		if (we == null)
			throw new WalletException("Wallet does not exists for id:" + walletId);
		return we.getBalance();
	}

	@Override
	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException
	{   
		Wallet fromWallet = this.walletRepository.getWalletById(fromId);
		Wallet toWallet = this.walletRepository.getWalletById(toId);
		if (fromWallet == null)
			throw new WalletException("From Wallet id does not exists");
		if (toWallet == null)
			throw new WalletException("To Wallet id does not exists");
		Double fromBalance = fromWallet.getBalance();
		if (fromBalance < amount)
			throw new WalletException("From wallet have insufficent balance:" + fromBalance);
		fromBalance -= amount;
		fromWallet.setBalance(fromBalance);
		Double toBalance = toWallet.getBalance();
		toBalance += amount;
		toWallet.setBalance(toBalance);
		this.walletRepository.updateWallet(fromWallet);
		this.walletRepository.updateWallet(toWallet);
		
		return true;
	}

	@Override
	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException 
	{
		Wallet foundWallet = this.walletRepository.getWalletById(walletId);
		if(foundWallet == null)
			throw new WalletException("Wallet not found to unregister");
		
		if(!foundWallet.getPassword().equals(password))
			throw new WalletException("Password does't match to unregister your account.");
		
		Wallet deletedWallet;
		try {
			deletedWallet = this.walletRepository.deleteWalletById(walletId);
		} catch (WalletException e) {

			throw new WalletException(e.getMessage());
		}
		return deletedWallet;
	}
	
}