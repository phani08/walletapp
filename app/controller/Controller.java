package com.wallet.app.controller;

import java.util.*;
import java.lang.*;


import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class Controller 
{
	public static void main(String[] args)
	{
	
		int id;
		String pswd=null;
		Scanner sc =new Scanner(System.in);
		
		WalletService walletService = new WalletServiceImpl();
		
		Wallet wallet;
		try {
			wallet = walletService.registerWallet(new Wallet(1, "A", 1000.0, "123"));
			wallet = walletService.registerWallet(new Wallet(2, "B", 2000.0, "12ab"));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		//System.out.println(wallet);
		
		int n;
		do {
			menu();
			n = sc.nextInt();
	
			switch(n)
			{
			
			case 1:
				System.out.println("****ADD account****");
				Wallet we = new Wallet();
				System.out.println("Enter the Id");
				we.setId(sc.nextInt());
				System.out.println("Enter your Name");
				we.setName(sc.next());
				System.out.println("Enter the Balance you wanted to add");
				we.setBalance(sc.nextDouble());
				System.out.println("Enter the Password");
				we.setPassword(sc.next());
				
				try {
					System.out.println(walletService.registerWallet(we));
				} catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				// System.out.println(wallet);
				 break;
				 
			case 2:
				System.out.println("****Login***");
				System.out.println("enter ur id");
				id=sc.nextInt();
				System.out.println("Enter the Password");
				pswd = sc.next();
				
				try {
					System.out.println(walletService.login(id, pswd));
				} 
				catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
				break;
				
			case 3:
				
				try {
					System.out.println("****Add funds****");
					System.out.println("Enter the id : ");
					int funds_id = sc.nextInt();
					System.out.println("Enter the amount: ");
					double amt = sc.nextDouble();
					
					System.out.println(walletService.addFundsToWallet(funds_id, amt));
				} 
				catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				break;
				
				
			case 4:
				System.out.println("***Balance Display***");
				int bal_id = sc.nextInt();
				try {
					System.out.println(walletService.showWalletBalance(bal_id));
				} catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 5:
				System.out.println("****Transfer Amount****");
				int fromId = sc.nextInt();
				int toId=sc.nextInt();
				Double amt=sc.nextDouble();
				try {
					System.out.println(walletService.fundTransfer(fromId, toId, amt));
				} catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 6:
				System.out.println("****Delete Account****");
				int walletId=sc.nextInt();
				String password = sc.next();
				try {
					System.out.println(walletService.unRegisterWallet(walletId, password));
				} catch (WalletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
			case 9:
				System.out.println("\nThank you for using our service\n");
				System.exit(0);
				break;
				
			default:
				System.out.println("\nInvalid input\n");
				break;
			}
			
		}while (n!= 9);
		
		}


	public static void menu() {

		System.out.println("List of Service");
		
		System.out.println("1 - Register account  "+  '\n'
							+ "2 - Login to Account  "+'\n'
							+ "3 - Add Funds to ur Account"+'\n'
							+ "4 - Show Balance amount"+'\n'
							+ "5 - Transfer amount"+'\n'
							+ "6 - delete account"+  '\n'
							+ "9 - Exit");
		
		System.out.println("Enter the service no. : ");
	}
	

}
