package com.example.iocnv.model;

import java.util.ArrayList;
import java.util.List;


public class Set {

	private List<Integer> O = new ArrayList<Integer>();
	private List<Integer> P = new ArrayList<Integer>();
	private List<Integer> L = new ArrayList<Integer>();
	private List<Integer> R = new ArrayList<Integer>();
	private List<Integer> pwd = new ArrayList<Integer>();
	private int number;
	
	public Set(int a) {
		PiLR(createRandom());
		number = a;
	}
	
	public void setInit() {
		O.clear();
		P.clear();
		L.clear();
		R.clear();
		PiLR(createRandom());	
	}

		public  void setL(int flag) {
			PiOP(createRandom(O, P, R));
			PiLR(createRandom(L));
			if (flag % 4 == 0) {
				if (L.size() == 1) {
				    pwd.add((Integer)L.get(0));
				} else {
					pwd.add(-1);
				}
				if (flag < number * 4) {
					setInit();
				}
			} 
		}

		public  void setR(int flag) {
			PiOP(createRandom(O, P, L));
			PiLR(createRandom(R));
			if (flag % 4 == 0) {
				if (L.size() == 1) {
					pwd.add((Integer)L.get(0));
				} else {
					pwd.add(-1);
				}
				if (flag < (number * 4 )) {
					setInit();
				}
			} 
		}	
	
	public int[] createRandom() {
		int number = 10;
		int[] random = new int[10];
		List<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) 
			arr.add(i);
		for (int j = 0; j < 10; j++) {
			int index = (int) (Math.random() * number);
			random[j] = (Integer) arr.get(index);
			arr.remove(index);
			number--;
		}
		return random;
	}
	
	public int[] createRandom(List<Integer> a) {
		int number = a.size();
		int number1 = number;
		int[] random = new int[number];
		List<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++)
			arr.add((Integer)a.get(i));
		for (int j = 0; j < number1; j++) {
			int index = (int) (Math.random() * number);
			random[j] = (Integer) arr.get(index);
			arr.remove(index);
			number--;
		}
		return random;
	}
	
	public int[] createRandom(List<Integer> a, List<Integer> b, List<Integer> c) {
		int number = a.size() + b.size() + c.size();
		int number1 = number;
		int[] random = new int[number];
		List<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++)
			arr.add((Integer)a.get(i));
		for (int i = 0; i < b.size(); i++)
			arr.add((Integer)b.get(i));
		for (int i = 0; i < c.size(); i++)
			arr.add((Integer)c.get(i));
		for (int j = 0; j < number1; j++) {
			int index = (int) (Math.random() * number);
			random[j] = (Integer) arr.get(index);
			arr.remove(index);
			number--;
		}
		return random;
	}
	
	//LRƽ��
	public void PiLR(int[] random) {
		int i;
		int j;
		L.clear();
		R.clear();
		for (i = 0; i < Math.ceil(((double)random.length)/2); i++) {
			L.add(random[i]);
		}
		for (j = i; j < random.length; j++) {
			R.add(random[j]);
		}
	} 
	
	//OPƽ��
	public void PiOP(int[] random) {
		int i;
		int j;
		O.clear();
		P.clear();
		for (i = 0; i < Math.ceil(((double)random.length)/2); i++) {
			O.add(random[i]);
		}
		for (j = i; j < random.length; j++) {
			P.add(random[j]);
		}
	}



	public int[] setDisplay() {
		int[] color = new int[10];
		for (int i = 0; i < L.size(); i++)
			color[L.get(i)] = 0;
		for (int i = 0; i < P.size(); i++)
			color[P.get(i)] = 0;
		for (int i = 0; i < R.size(); i++)
			color[R.get(i)] = 1;
		for (int i = 0; i < O.size(); i++)
			color[O.get(i)] = 1;
		return color;
	}
	
	public int[] getPwd(int number) {
		int[] password = new int[number];
			password[0] = pwd.get(0);
			password[1] = pwd.get(1);
			password[2] = pwd.get(2);
			password[3] = pwd.get(3);
		if (number == 6) {
			password[4] = pwd.get(4);
			password[5] = pwd.get(5);
		} else if (number == 8){
			password[4] = pwd.get(4);
			password[5] = pwd.get(5);
			password[6] = pwd.get(6);
			password[7] = pwd.get(7);
		}
		return password;
	}

	public boolean delPwd() {
		if (pwd.size() > 0) {
			pwd.remove(pwd.size() - 1);
			return true;
		}
		return false;
	}
	
}
