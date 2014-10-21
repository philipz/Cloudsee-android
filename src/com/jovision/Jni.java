package com.jovision;

import neo.droid.cloudsee.Consts;
import neo.droid.cloudsee.JVNetConst;
import neo.droid.cloudsee.MainApplication;

/**
 * 所有与 NDK 交互的接口都在这儿
 * 
 * @author neo
 * 
 */
public class Jni {

	/**
	 * dummy test foo
	 * 
	 */
	public static native byte[] foo(byte[] array);

	public static native void setStat(boolean on);

	/**
	 * 获取底层库版本
	 * 
	 * @return json: {"jni":"xx","net":"xx"}
	 */
	public static native String getVersion();

	/**
	 * 初始化，参考 {@link JVSUDT#JVC_InitSDK(int, Object)}
	 * 
	 * @param handle
	 *            回调句柄，要传 MainApplication 的实例对象哦，因为回调方式是：<br />
	 *            {@link MainApplication#onJniNotify(int, int, int, Object)}
	 * @param port
	 *            本地端口
	 * @param path
	 *            日志路径
	 * @return
	 */
	public static native boolean init(Object handle, int port, String path);

	/**
	 * 卸载，参考 {@link JVSUDT#JVC_ReleaseSDK()}
	 * 
	 */
	public static native void deinit();

	/**
	 * 搜索局域网服务端，参考 {@link JVSUDT#JVC_StartLANSerchServer(int, int)}
	 * 
	 * @param localPort
	 *            默认 9400
	 * @param serverPort
	 *            默认 6666
	 * @return
	 */
	public static native int searchLanServer(int localPort, int serverPort);

	/**
	 * 停止搜索局域网服务端，参考 {@link JVSUDT#JVC_StopLANSerchServer()}
	 * 
	 */
	public static native void stopSearchLanServer();

	/**
	 * 获取通道个数，参考 {@link JVSUDT#JVC_WANGetChannelCount(String, int, int)}
	 * 
	 * @param group
	 * @param cloudSeeId
	 * @param timeout
	 *            注意：单位是秒
	 * @return
	 * 
	 */
	public static native int getChannelCount(String group, int cloudSeeId,
			int timeout);

	/**
	 * 搜索局域网设备，参考
	 * {@link JVSUDT#JVC_MOLANSerchDevice(String, int, int, int, String, int)}
	 * 
	 * @param group
	 * @param cloudSeeId
	 * @param cardType
	 * @param variety
	 * @param deviceName
	 * @param timeout
	 *            单位是毫秒
	 * @param frequence
	 * @return
	 */
	public static native int searchLanDevice(String group, int cloudSeeId,
			int cardType, int variety, String deviceName, int timeout,
			int frequence);

	/**
	 * 开启快速链接服务，参考 {@link JVSUDT#JVC_EnableHelp(boolean, int)}
	 * 
	 * @param enable
	 * @param typeId
	 *            enable == ture 时
	 *            <ul>
	 *            <li>1: 使用者是开启独立进程的云视通小助手</li>
	 *            <li>2: 使用者是云视通客户端，支持独立进程的云视通小助手</li>
	 *            <li>3: 使用者是云视通客户端，不支持独立进程的云视通小助手</li>
	 *            </ul>
	 * 
	 *            难以理解的语言
	 * @param maxLimit
	 *            允许最大限制
	 * 
	 * @return
	 */
	public static native boolean enableLinkHelper(boolean enable, int typeId,
			int maxLimit);

	/**
	 * 设置连接小助手，参考 {@link JVSUDT#JVC_SetHelpYSTNO(byte[], int)}
	 * 
	 * @param json
	 *            [{gid: "A", no: 361, channel: 1, name: "abc", pwd:
	 *            "123"},{gid: "A", no: 362, channel: 1, name: "abc", pwd:
	 *            "123"}]
	 * @return
	 */
	public static native boolean setLinkHelper(String json);

	/**
	 * 获取已设置的云视通列表，参考 {@link JVSUDT#JVC_GetHelpYSTNO(byte[], int)}
	 * 
	 * @return json [{cno: "A361", enable: false},{no: "A362", enable: false}]
	 */
	public static native String getAllDeviceStatus();

	/**
	 * 检查设备是否在线，参考 {@link JVSUDT#JVC_GetYSTStatus(String, int, int)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param group
	 * @param cloudSeeId
	 * @param timeout
	 *            单位是秒
	 * @return
	 */
	public static native int isDeviceOnline(String group, int cloudSeeId,
			int timeout);

	/**
	 * 连接，参考
	 * {@link JVSUDT#JVC_Connect(int, int, String, int, String, String, int, String, boolean, int, boolean, int, Object)}
	 * 
	 * @param index
	 *            窗口索引，从 0 开始
	 * @param channel
	 *            设备通道，从 1 开始
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @param cloudSeeId
	 * @param groupId
	 * @param isLocalDetect
	 * @param turnType
	 * @param isPhone
	 * @param connectType
	 * @param surface
	 * @param isTryOmx
	 * @return 播放线程是否成功启动
	 */
	public static native boolean connect(int index, int channel, String ip,
			int port, String username, String password, int cloudSeeId,
			String groupId, boolean isLocalDetect, int turnType,
			boolean isPhone, int connectType, Object surface, boolean isTryOmx);

	/**
	 * 设置当前解码方式
	 * 
	 * @param index
	 *            窗口索引
	 * @param isOmx
	 *            是否硬解
	 * @return 设置是否生效
	 */
	public static native boolean setOmx(int index, boolean isOmx);

	/**
	 * 设置下载文件路径
	 * 
	 * @param fileName
	 */
	public static native void setDownloadFileName(String fileName);

	/**
	 * 获取下载文件路径
	 * 
	 * @return
	 */
	public static native String getDownloadFileName();

	/**
	 * 暂停底层显示
	 * 
	 * @param index
	 *            窗口索引
	 * @return
	 */
	public static native boolean pause(int index);

	/**
	 * 恢复底层显示
	 * 
	 * @param index
	 *            窗口索引
	 * @param surface
	 * @return
	 */
	public static native boolean resume(int index, Object surface);

	/**
	 * 将指定通道的视频快进到最新内容
	 * 
	 * @param index
	 *            窗口索引
	 */
	public static native boolean fastForward(int index);

	/**
	 * 开始录制，参考
	 * {@link JVSUDT#StartRecordMP4(String, int, int, int, int, int, double, int)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param path
	 * @param video
	 * @param audio
	 * @return
	 */
	public static native boolean startRecord(int index, String path,
			boolean enableVideo, boolean enableAudio);

	/**
	 * 检查对应窗口是否处于录像状态，TODO: 现在只有单路可用
	 * 
	 * @param index
	 *            窗口索引
	 * @return
	 */
	public static native boolean checkRecord(int index);

	/**
	 * 停止录制，参考 {@link JVSUDT#StopRecordMP4(int)}
	 * 
	 * @return
	 */
	public static native boolean stopRecord();

	/**
	 * 截图，参考 {@link JVSUDT#SaveCapture(int)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param name
	 *            待保存的文件名
	 * @param quality
	 *            画面质量
	 * @return
	 */
	public static native boolean screenshot(int index, String name, int quality);

	/**
	 * 断开，参考 {@link JVSUDT#JVC_DisConnect(int)}
	 * 
	 * @param index
	 *            窗口索引
	 */
	public static native boolean disconnect(int index);

	/**
	 * 清理本地缓存，参考 {@link JVSUDT#JVC_ClearBuffer(int)}
	 * 
	 * @param index
	 *            窗口索引
	 */
	public static native boolean clearBuffer(int index);

	/**
	 * 查询某个设备是否被搜索出来
	 * 
	 * @param groudId
	 *            组标识
	 * @param cloudSeeId
	 *            云视通编号
	 * @param timeout
	 *            超时时间，毫秒
	 * @return 调用是否成功，等回调
	 */
	public static native boolean queryDevice(String groudId, int cloudSeeId,
			int timeout);

	/**
	 * 发送字节数据，参考 {@link JVSUDT#JVC_SendData(int, byte, byte[], int)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param data
	 * @param size
	 */
	public static native boolean sendBytes(int index, byte uchType,
			byte[] data, int size);

	/**
	 * 发送音频数据 {@link JVSUDT#JVC_SendAudioData(int, byte, byte[], int)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param data
	 * @param size
	 */
	public static native boolean sendAudioData(int index, byte uchType,
			byte[] data, int size);

	/**
	 * 发送整数数据，参考 {@link JVSUDT#JVC_SendPlaybackData(int, byte, int, int)}
	 * 
	 * 实际调用 {@link #sendCmd(int, byte, byte[], int)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param data
	 */
	public static native boolean sendInteger(int index, byte uchType, int data);

	/**
	 * 发送字符串数据
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param data
	 */

	/**
	 * 发送字符串数据
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 *            发送类型
	 * @param isExtend
	 *            是否扩展消息
	 * @param count
	 *            扩展包数量
	 * @param type
	 *            扩展消息类型
	 * @param data
	 *            数据
	 */
	public static native boolean sendString(int index, byte uchType,
			boolean isExtend, int count, int type, String data);

	{
		int index = 0;
		byte uchType = 0;

		int mode = 0;
		int switcher = 0;

		int type = 0;
		int flag = 0;
		String ssid = "";
		String pwd = "";
		String auth = "";
		String enc = "";

		String custom = "";

		// [Neo] 设置存储模式
		Jni.sendString(index, uchType, true, Consts.COUNT_EX_STORAGE,
				Consts.TYPE_EX_STORAGE_SWITCH,
				String.format(Consts.FORMATTER_STORAGE_MODE, mode));

		// [Neo] 获取存储模式
		Jni.sendString(index, uchType, false, 0, Consts.TYPE_GET_PARAM, null);

		// [Neo] 结果检查，通过判断 TextData 的 flag 是否等于 100

		// [Neo] 切换对讲
		Jni.sendString(index, uchType, false, 0, Consts.TYPE_SET_PARAM,
				String.format(Consts.FORMATTER_TALK_SWITCH, switcher));

		// [Neo] 设置 wifi
		Jni.sendString(index, uchType, true, Consts.COUNT_EX_NETWORK, type,
				String.format(Consts.FORMATTER_SET_WIFI, flag, ssid, pwd));

		// [Neo] 保存 wifi
		Jni.sendString(index, uchType, true, Consts.COUNT_EX_NETWORK, type,
				String.format(Consts.FORMATTER_SAVE_WIFI, flag, ssid, pwd,
						auth, enc));

		// [Neo] 设置 DHCP
		Jni.sendString(index, uchType, true, Consts.COUNT_EX_NETWORK,
				Consts.TYPE_EX_SET_DHCP,
				String.format(Consts.FORMATTER_SET_DHCP, 1, 1, 1, 1, 1));

		// [Neo] 设置码流
		Jni.sendString(index, uchType, false, 0, Consts.TYPE_SET_PARAM,
				String.format(Consts.FORMATTER_SET_BPS_FPS, 1, 1, 1, 1));

		// [Neo] 切换码流、设置设备名称、设置存储
		Jni.sendString(index, uchType, false, 0, Consts.TYPE_SET_PARAM, custom);

		// [Neo] 翻转视频
		Jni.sendString(index, uchType, true, Consts.COUNT_EX_SENSOR,
				Consts.TYPE_EX_SENSOR, custom);

		// [Neo] 更新设备
		Jni.sendString(index, uchType, true, Consts.TYPE_EX_UPDATE,
				Consts.COUNT_EX_UPDATE, null);

		// [Neo] 门瓷与手环
		Jni.sendString(index, uchType, false, type, 0, custom);
	}

	/**
	 * 发送聊天命令，参考 {@link JVSUDT#JVC_SendTextData(int, byte, int, int)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param size
	 * @param flag
	 */
	public static native boolean sendTextData(int index, byte uchType,
			int size, int flag);

	/**
	 * 发送命令，参考 {@link JVSUDT#JVC_SendCMD(int, byte, byte[], int)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param data
	 * @param size
	 * @return
	 */
	public static native int sendCmd(int index, byte uchType, byte[] data,
			int size);

	/**
	 * 设置 wifi，参考
	 * {@link JVSUDT#JVC_SetWifi(int, byte, String, String, int, int)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param ssid
	 * @param password
	 * @param flag
	 * @param tag
	 */
	public static native boolean setWifi(int index, byte uchType, String ssid,
			String password, int flag, int tag);

	/**
	 * 保存 wifi 配置，参考
	 * {@link JVSUDT#JVC_SaveWifi(int, byte, String, String, int, int, String, String)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param ssid
	 * @param password
	 * @param flag
	 * @param type
	 * @param auth
	 * @param enc
	 */
	public static native boolean saveWifi(int index, byte uchType, String ssid,
			String password, int flag, int type, String auth, String enc);

	/**
	 * 设置 AP，参考 {@link JVSUDT#JVC_ManageAP(int, byte, String)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param json
	 */
	public static native boolean setAccessPoint(int index, byte uchType,
			String json);

	/**
	 * 设置 DHCP，参考
	 * {@link JVSUDT#JVC_SetDHCP(int, byte, int, String, String, String, String)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param dhcp
	 * @param ip
	 * @param mask
	 * @param gateway
	 * @param dns
	 * @return
	 */
	public static native boolean setDhcp(int index, byte uchType, int dhcp,
			String ip, String mask, String gateway, String dns);

	/**
	 * 设置码流和帧率，参考
	 * {@link JVSUDT#JVC_SetStreams(int, byte, int, int, int, int, int)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param channel
	 * @param width
	 * @param height
	 * @param mbps
	 * @param fps
	 */
	public static native boolean setBpsAndFps(int index, byte uchType,
			int channel, int width, int height, int mbps, int fps);

	/**
	 * 修改码流，参考 {@link JVSUDT#JVC_ChangeStreams(int, byte, byte[])}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param cmd
	 */
	public static native boolean changeStream(int index, byte uchType,
			String cmd);

	/**
	 * 设置设备名称，参考 {@link JVSUDT#JVC_SetDeviceName(int, byte, byte[])}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param json
	 */
	public static native boolean setDeviceName(int index, byte uchType,
			String cmd);

	/**
	 * 设置存储地址，参考 {@link JVSUDT#JVC_SetStorage(int, byte, byte[])}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param cmd
	 */
	public static native boolean setStorage(int index, byte uchType, String cmd);

	/**
	 * 翻转视频，参考 {@link JVSUDT#JVC_TurnVideo(int, byte, byte[])}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 * @param cmd
	 */
	public static native boolean rotateVideo(int index, byte uchType, String cmd);

	/**
	 * 设备升级，参考 {@link JVSUDT#JVC_DeviceUpdate(int, byte)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param index
	 *            窗口索引
	 * @param uchType
	 */
	public static native boolean updateDevice(int index, byte uchType);

	/**
	 * 启用底层日志打印，参考 {@link JVSUDT#JVC_EnableLog(boolean)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param enable
	 */
	public static native void enableLog(boolean enable);

	/**
	 * 设置日志和提示信息区域语言，参考 {@link JVSUDT#JVC_SetLanguage(int)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param typeId
	 *            参考 {@link JVNetConst#JVN_LANGUAGE_ENGLISH}，
	 *            {@link JVNetConst#JVN_LANGUAGE_CHINESE}
	 */
	public static native void setLanguage(int typeId);

	/**
	 * 修改服务器域名，参考 {@link JVSUDT#JVC_SetDomainName(String, String)}
	 * 
	 * // [Neo] TODO 未验证
	 * 
	 * @param domainName
	 * @param pathName
	 * @return
	 */
	public static native boolean changeDomain(String domainName, String pathName);

	/**
	 * 修改指定窗口播放标识位，参考 {@link JVSUDT#ChangePlayFalg(int, int)}
	 * 
	 * @param index
	 *            窗口索引
	 * @param enable
	 * @return
	 */
	public static native boolean enablePlayback(int index, boolean enable);

	/**
	 * 获取指定窗口是否处于远程回放状态
	 * 
	 * @param index
	 *            窗口索引
	 * @return
	 */
	public static native boolean isPlayback(int index);

	/**
	 * 修改指定窗口音频标识位
	 * 
	 * @param index
	 *            窗口索引
	 * @param enable
	 *            是否播放 Normaldata 的音频数据
	 * @return
	 */
	public static native boolean enablePlayAudio(int index, boolean enable);

	/**
	 * 获取指定窗口是否正在播放音频
	 * 
	 * @param index
	 *            窗口索引
	 * @param enable
	 * @return
	 */
	public static native boolean isPlayAudio(int index);

	/**
	 * 设置显示图像的顶点坐标(坐标系原点在 Surface 左下顶点)和长宽
	 * 
	 * @param index
	 *            窗口索引
	 * @param left
	 *            图像左坐标
	 * @param bottom
	 *            图像底坐标
	 * @param width
	 *            图像宽
	 * @param height
	 *            图像高
	 * @return
	 */
	public static native boolean setViewPort(int index, int left, int bottom,
			int width, int height);

	/**
	 * 初始化音频编码
	 * 
	 * @param type
	 *            类型，amr/alaw/ulaw，参考 {@link Consts#JAE_ENCODER_SAMR},
	 *            {@link Consts#JAE_ENCODER_ALAW},
	 *            {@link Consts#JAE_ENCODER_ULAW}
	 * @param sampleRate
	 * @param channelCount
	 * @param bitCount
	 * @param block
	 *            PCM 640
	 * @return
	 */
	public static native boolean initAudioEncoder(int type, int sampleRate,
			int channelCount, int bitCount, int block);

	/**
	 * 编码一帧
	 * 
	 * @param data
	 * @return 失败的话返回 null
	 */
	public static native byte[] encodeAudio(byte[] data);

	/**
	 * 销毁音频编码，如果要切换编码参数，必须销毁的重新创建
	 * 
	 * @return
	 */
	public static native boolean deinitAudioEncoder();

	/**
	 * TCP 连接，参考
	 * {@link JVSUDT#JVC_TCPConnect(int, int, String, int, String, String, int, String, boolean, int, int)}
	 * 
	 * // [Neo] TODO 未实现
	 * 
	 * @param index
	 *            窗口索引
	 * @param channel
	 *            通道号，从 0 开始
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @param cloudSeeId
	 * @param groupId
	 * @param isLocalDetect
	 * @param connectType
	 * @param turnType
	 * 
	 */
	public static native boolean tcpConnect(int index, int channel, String ip,
			int port, String username, String password, int cloudSeeId,
			String groupId, boolean isLocalDetect, int connectType, int turnType);

}
