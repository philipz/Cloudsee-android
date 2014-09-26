package com.jovision.utils;

import java.util.ArrayList;

import com.jovision.Consts;
import com.jovision.bean.BeanUtil;
import com.jovision.bean.UserBean;
import com.jovision.commons.MySharedPreference;

public class UserUtil {
	/** 1.查询所有用户 */
	public static ArrayList<UserBean> getUserList() {
		ArrayList<UserBean> userList = BeanUtil
				.stringToUserList(MySharedPreference
						.getString(Consts.LOCAL_USER_LIST));
		return userList;
	}

	/** 2.增加用户 */
	public static ArrayList<UserBean> addUser(UserBean user) {
		ArrayList<UserBean> userList = BeanUtil
				.stringToUserList(MySharedPreference
						.getString(Consts.LOCAL_USER_LIST));
		userList.add(user);
		MySharedPreference.putString(Consts.LOCAL_USER_LIST,
				userList.toString());
		return userList;
	}

	/** 3.删除用户 */
	public static ArrayList<UserBean> deleteUser(UserBean user) {
		ArrayList<UserBean> userList = BeanUtil
				.stringToUserList(MySharedPreference
						.getString(Consts.LOCAL_USER_LIST));
		userList.remove(user);
		MySharedPreference.putString(Consts.LOCAL_USER_LIST,
				userList.toString());
		return userList;
	}

	/** 4.修改用户 */
	public static ArrayList<UserBean> editUser(UserBean user) {
		ArrayList<UserBean> userList = BeanUtil
				.stringToUserList(MySharedPreference
						.getString(Consts.LOCAL_USER_LIST));
		userList.remove(user);
		int size = userList.size();
		for (int i = 0; i < size; i++) {
			if (userList.get(i).getUserName().equals(user.getUserName())) {
				userList.get(i).setUserName(user.getUserName());
				userList.get(i).setUserPwd(user.getUserPwd());
				userList.get(i).setUserEmail(user.getUserEmail());
				userList.get(i).setLastLogin(user.getLastLogin());
				userList.get(i).setPrimaryID(user.getPrimaryID());
			}
		}
		MySharedPreference.putString(Consts.LOCAL_USER_LIST,
				userList.toString());
		return userList;
	}

	/** 5.获取上次登陆用户 */
	public static UserBean getLastUser() {
		UserBean lastUser = null;
		ArrayList<UserBean> userList = BeanUtil
				.stringToUserList(MySharedPreference
						.getString(Consts.LOCAL_USER_LIST));
		int size = userList.size();
		for (int i = 0; i < size; i++) {
			if (1 == userList.get(i).getLastLogin()) {
				lastUser = userList.get(i);
			}
		}
		return lastUser;
	}
}