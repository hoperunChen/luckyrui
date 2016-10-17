package com.luckyrui.code_review;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luckyrui.utils.StringUtil;

/**
 * 2016周年庆业务类:</br>
 * 1.领取奖励:takePrize 2.补签:retroactive
 * 
 * @author chenrui
 * @date 2016年8月25日下午1:26:03
 * @version 2016_Anniversary
 */
public class AnniversaryTakeService {
	public static Log log = LogFactory.getLog(AnniversaryTakeService.class);

	/********************* final *****************/

	/**
	 * 现金券
	 */
	private static final String PRIZE_NAME_COUPON = "现金券";

	/**
	 * 能量
	 */
	private static final String PRIZE_NAME_ENERGY = "能量";

	/**
	 * 915大礼包
	 */
	private static final String PRIZE_NAME_915BAG = "915大礼包";

	/**
	 * 流量
	 */
	private static final String PRIZE_NAME_FLOW = "流量";

	/**
	 * 马达加斯加单人7日游
	 */
	private static final String PRIZE_NAME_MADAGASCAR = "马达加斯加";

	
	/**
	 * 哈根达斯
	 */
	private static final String PRIZE_NAME_HAAGENDAZS = "哈根达斯";
	
	/**
	 * 电影票
	 */
	private static final String PRIZE_NAME_CINEMA = "电影票";
	
	/**
	 * 爱奇艺
	 */
	private static final String PRIZE_NAME_AIQIYI = "爱奇艺";
	
	/********************* final *****************/

	/**
	 * 2016周年庆业务类
	 */
	AnniversaryService anniversaryService;
	/**
	 * 用户dao
	 */
	UserDao userDao;
	/**
	 * 周年庆奖品排期表
	 */
	AnniversaryGiftScheduleDao anniversaryGiftScheduleDao;
	/**
	 * 周年庆奖品获得表
	 */
	AnniversaryGiftListDao anniversaryGiftListDao;
	/**
	 * 现金券
	 */
	UserCouponService userCouponService;
	/**
	 * 周年庆能量业务类
	 */
	AnniversaryRateService anniversaryRateService;
	/**
	 * 周年庆礼品表-无固定数量实物礼品
	 */
	AnniversaryGiftUnsizeDao anniversaryGiftUnsizeDao;
	/**
	 * 周年庆礼品表-有固定数量实物礼品
	 */
	AnniversaryGiftSizeDao anniversaryGiftSizeDao;
	/**
	 * 周年庆查询service
	 */
	AnniversaryQueryService anniversaryQueryService;
	/**
	 * 每日领取表Dao
	 */
	AnniversaryDailyDao anniversaryDailyDao;
	/**
	 * 周年庆活动总表
	 */
	AnniversaryDao anniversaryDao;
	/**
	 * 补卡机会dao
	 */
	AnniversaryChanceDao anniversaryChanceDao;
	/**
	 * 用户service
	 */
	UserService userService;
	/**
	 * 发送短信站内心service
	 */
	SendMessageService sendMessageService;

	/********************* getter/setter ***********************/

	public UserService getUserService() {
		return userService;
	}

	public SendMessageService getSendMessageService() {
		return sendMessageService;
	}

	public void setSendMessageService(SendMessageService sendMessageService) {
		this.sendMessageService = sendMessageService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AnniversaryService getAnniversaryService() {
		return anniversaryService;
	}

	public AnniversaryDailyDao getAnniversaryDailyDao() {
		return anniversaryDailyDao;
	}

	public void setAnniversaryDailyDao(AnniversaryDailyDao anniversaryDailyDao) {
		this.anniversaryDailyDao = anniversaryDailyDao;
	}

	public AnniversaryQueryService getAnniversaryQueryService() {
		return anniversaryQueryService;
	}

	public void setAnniversaryQueryService(AnniversaryQueryService anniversaryQueryService) {
		this.anniversaryQueryService = anniversaryQueryService;
	}

	public AnniversaryGiftUnsizeDao getAnniversaryGiftUnsizeDao() {
		return anniversaryGiftUnsizeDao;
	}

	public void setAnniversaryGiftUnsizeDao(AnniversaryGiftUnsizeDao anniversaryGiftUnsizeDao) {
		this.anniversaryGiftUnsizeDao = anniversaryGiftUnsizeDao;
	}

	public AnniversaryGiftSizeDao getAnniversaryGiftSizeDao() {
		return anniversaryGiftSizeDao;
	}

	public void setAnniversaryGiftSizeDao(AnniversaryGiftSizeDao anniversaryGiftSizeDao) {
		this.anniversaryGiftSizeDao = anniversaryGiftSizeDao;
	}

	public AnniversaryRateService getAnniversaryRateService() {
		return anniversaryRateService;
	}

	public void setAnniversaryRateService(AnniversaryRateService anniversaryRateService) {
		this.anniversaryRateService = anniversaryRateService;
	}

	public AnniversaryGiftListDao getAnniversaryGiftListDao() {
		return anniversaryGiftListDao;
	}

	public void setAnniversaryGiftListDao(AnniversaryGiftListDao anniversaryGiftListDao) {
		this.anniversaryGiftListDao = anniversaryGiftListDao;
	}

	public UserCouponService getUserCouponService() {
		return userCouponService;
	}

	public void setUserCouponService(UserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}

	public AnniversaryGiftScheduleDao getAnniversaryGiftScheduleDao() {
		return anniversaryGiftScheduleDao;
	}

	public void setAnniversaryGiftScheduleDao(AnniversaryGiftScheduleDao anniversaryGiftScheduleDao) {
		this.anniversaryGiftScheduleDao = anniversaryGiftScheduleDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setAnniversaryService(AnniversaryService anniversaryService) {
		this.anniversaryService = anniversaryService;
	}

	public AnniversaryDao getAnniversaryDao() {
		return anniversaryDao;
	}

	public void setAnniversaryDao(AnniversaryDao anniversaryDao) {
		this.anniversaryDao = anniversaryDao;
	}

	public AnniversaryChanceDao getAnniversaryChanceDao() {
		return anniversaryChanceDao;
	}

	public void setAnniversaryChanceDao(AnniversaryChanceDao anniversaryChanceDao) {
		this.anniversaryChanceDao = anniversaryChanceDao;
	}
	/********************* getter/setter ***********************/

	/********************* public method **********************/

	/**
	 * 领取奖品
	 * 
	 * @param conn
	 * @param uid
	 *            用户id
	 * @param prizeType
	 *            1-30:每日奖励,100:能量30%奖励,101:能量60%奖励,102:能量100%奖励
	 * @return {message:{code:0,message:''}} code=0:成功
	 * @author chenrui
	 * @date 2016年8月25日 下午5:13:14
	 * @version 2016_Anniversary
	 * @throws Exception
	 */
	public Map<String, Object> takePrize(Connection conn, long uid, String prizeType) throws Exception {
		// 判断数据库连接
		if (null == conn) {
			throw new ConnNullException("数据库连接为空");
		}
		// 验证用户id
		if (uid <= 0) {
			// 用户不存在
			return getFailRtn(-1, "用户不存在");
		}
		if (StringUtils.isEmpty(prizeType)) {
			// 奖品类型为空
			return getFailRtn(-1, "奖品类型为空");
		}
		log.info(String.format("开始领取周年庆奖品:用户id:[%d];奖品类型:[%s]", uid, prizeType));
		// 查询用户
		Map<String, String> user = userDao.queryUserById(conn, uid);
		// 验证用户存在
		if (null == user || StringUtil.isEmtpy(user.get("id")) || user.get("enable").trim().equals("2")) {
			// 用户不存在或被禁用
			return getFailRtn(-1, "用户不存在或被禁用");
		}
		// 验证用户是否实名
		if (!userService.isAuth(uid)) {
			return getFailRtn(-100, "用户未实名");
		}
		// 判断是否在活动时间
		if (!anniversaryService.checkAnniversaryTime(conn)) {
			log.error(String.format("用户%d[请求领取奖励]不在活动时间", uid));
			return getFailRtn(-101, "不在活动时间");
		}

		int prizeTypeI = Integer.parseInt(prizeType); // 奖品类型

		// 验证用户是否已经领取奖品
		Map<String, String> acquiredGift = anniversaryGiftListDao.queryGiftByDaynum(conn, uid, prizeTypeI);
		if (null != acquiredGift && null != acquiredGift.get("count") && !StringUtils.isBlank(acquiredGift.get("count"))
				&& !acquiredGift.get("count").equals("0")) {
			return getFailRtn(2, "重复领取");
		}

		if (prizeTypeI < 1 && prizeTypeI > 30 && prizeTypeI < 100 && prizeTypeI > 102) {
			log.error("不正确的奖品类型:" + prizeTypeI);
			return getFailRtn(-1, "奖品类型不正确");
		}
		Map<String, String> _temp = null;

		if (prizeTypeI < 100) {
			// 如果是每日领取,判断用户是否有可领取次数
			_temp = anniversaryQueryService.getAnniversaryDailyCount(uid, conn);
			// 可领取次数
			if (null == _temp || StringUtils.isBlank(_temp.get("notGiftCount"))
					|| Integer.parseInt(_temp.get("notGiftCount")) <= 0) {
				//
				log.error("用户[" + uid + "]没有可领取次数");
				return getFailRtn(-1, "用户[" + uid + "]没有可领取次数");
			}
			if (null == _temp || StringUtils.isBlank(_temp.get("getGiftCount"))
					|| (Integer.parseInt(_temp.get("getGiftCount")) + 1) != prizeTypeI) {
				// 用户未按照顺序领取
				log.error("请先领取" + (Integer.parseInt(_temp.get("getGiftCount")) + 1) + "日礼品");
				return getFailRtn(-1, "请先领取" + (Integer.parseInt(_temp.get("getGiftCount")) + 1) + "日礼品");
			}
		} else {
			// 用户能量
			_temp = anniversaryQueryService.getAnniversaryRate(uid, conn);
			if (null == _temp || StringUtils.isBlank(_temp.get("rate"))) {

			} else if (prizeTypeI == 100 && new BigDecimal(_temp.get("rate")).compareTo(new BigDecimal(30)) < 0) {
				return getFailRtn(-1, "能量值不足30%");
			} else if (prizeTypeI == 101 && new BigDecimal(_temp.get("rate")).compareTo(new BigDecimal(60)) < 0) {
				return getFailRtn(-1, "能量值不足60%");
			} else if (prizeTypeI == 102 && new BigDecimal(_temp.get("rate")).compareTo(new BigDecimal(100)) < 0) {
				return getFailRtn(-1, "能量值不足100%");
			}
		}

		try {
			// 领取奖品
			String takeRtn = takeDayPrize(conn, uid, prizeTypeI);
			if (StringUtils.isNotBlank(takeRtn)) {
				return getFailRtn(-1, takeRtn);
			}
			// 添加每日领取记录
			if (prizeTypeI < 100) {
				long dailyRtn = -1;
				// 查询是否存在日期号对应的领取信息
				dailyRtn = insertOrUpdateDaily(conn, uid, prizeTypeI);
				if (dailyRtn < 0) {
					log.error("添加每日领取记录失败");
					return getFailRtn(-1, "添加每日领取记录失败");
				}
			} else {
				// 发送能量礼包站内信和
				sendMessage(conn, uid, prizeTypeI, null, null);
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			log.error("[并发领取礼品]唯一约束冲突,代表已经存在数据,rollback 返回失败:uid=" + uid + ",prizeType" + prizeTypeI);
			conn.rollback();
			return getFailRtn(2, "重复领取");
		}

		log.info("用户" + uid + "周年庆" + prizeType + "日奖品领取成功");
		// 构造返回
		Map<String, Object> data = getTakeRtnData(conn, uid, prizeTypeI);
		return getSuccRtn("领取成功", data);
	}

	/**
	 * 领取奖品 (无connection)
	 * 
	 * @param uid
	 *            用户id
	 * @param prizeType
	 *            礼品类型
	 * @return
	 * @throws SQLException
	 * @author chenrui
	 * @date 2016年8月25日 下午5:30:05
	 * @version 2016_Anniversary
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> takePrize(long uid, String prizeType) throws SQLException {
		Map<String, Object> rtn = getFailRtn(-1, "查询失败");
		Connection conn = null;
		try {
			conn = MySQL.getConnection();

			// 领取奖励
			rtn = takePrize(conn, uid, prizeType);

			// 判断成功状态:成功提交,失败回滚
			Map<String, Object> message = (Map<String, Object>) rtn.get("message");
			if (null != message) {
				if ((Long) message.get("code") < 0) {
					conn.rollback();
				} else {
					conn.commit();
				}
			}
		} catch (Exception e) {
			log.error("领取奖励失败");
			e.printStackTrace();
			conn.rollback();
			return getFailRtn(-1, "领取奖励失败");
		} finally {
			if (null != conn)
				//conn.close();
		}
		return rtn;
	}

	/**
	 * 弥补机会
	 * 
	 * @param uid
	 *            用户id
	 * @return
	 * @author zhaowenjuan
	 * @date 2016年8月26日 下午8:28:30
	 * @version 2016_Anniversary
	 * @throws SQLException
	 */
	public Map<String, Object> retroactive(long uid) throws SQLException {
		Connection conn = null;
		Map<String, String> data = new HashMap<String, String>();
		try {
			long result = 1L;
			//
			conn = MySQL.getConnection();
			data = anniversaryQueryService.getAnniversaryDailyCount(uid, conn);
			if (!anniversaryService.checkAnniversaryTime(conn)) {
				log.info("2016周年庆活动时间已经过去");
				return getSuccRtn("不在活动时间内", data);
			}
			if (data == null) {
				return getFailRtn(-1, "失败");
			}
			int chanceCount = Integer.parseInt(data.get("chanceCount"));
			int missCount = Integer.parseInt(data.get("missCount"));
			if (chanceCount == 0) {
				return getFailRtn(-1, "没有补签次数");
			}
			if (missCount == 0) {
				return getFailRtn(-1, "没有错过天数");
			}
			// 如果missCount>=chanceCount,t_anniversary_daily表中插入chanceCount条数据，
			// 如果missCount<chanceCount,t_anniversary_daily表中插入missCount条数据
			int day_num = 0;
			Map<String, String> dayCount = anniversaryDailyDao.getAnniversaryMaxDayCountByUserId(conn, uid);
			if (StringUtils.isNotBlank(dayCount.get("max"))) {
				day_num = Integer.parseInt(dayCount.get("max"));
			}
			Map<String, String> anniversaryDailyInfo = new HashMap<String, String>();
			if (missCount >= chanceCount) {
				result = anniversaryChanceDao.updateAnniversaryChanceInfoByUserIdAndNum(conn, uid, chanceCount);
				if (result <= 0) {
					conn.rollback();
					log.info("弥补机会--更新弥补次数失败，userId=" + uid);
					return getFailRtn(-1, "弥补机会--更新弥补次数失败");
				}
				for (int i = 0; i < chanceCount; i++) {
					day_num += 1;
					anniversaryDailyInfo.put("userid", uid + "");
					anniversaryDailyInfo.put("day_num", day_num + "");
					anniversaryDailyInfo.put("status", "1");
					result = anniversaryDailyDao.insertAnniversaryDailyInfo(conn, anniversaryDailyInfo);
					if (result <= 0) {
						conn.rollback();
						log.info("弥补机会--添加每日领取表数据失败，userId=" + uid);
						return getFailRtn(-1, "弥补机会--添加每日领取表数据失败");
					}
				}
			} else {
				result = anniversaryChanceDao.updateAnniversaryChanceInfoByUserIdAndNum(conn, uid, missCount);
				if (result <= 0) {
					conn.rollback();
					log.info("弥补机会--更新弥补次数失败，userId=" + uid);
					return getFailRtn(-1, "弥补机会--更新弥补次数失败");
				}
				for (int i = 0; i < missCount; i++) {
					day_num += 1;
					anniversaryDailyInfo.put("userid", uid + "");
					anniversaryDailyInfo.put("day_num", day_num + "");
					anniversaryDailyInfo.put("status", "1");
					result = anniversaryDailyDao.insertAnniversaryDailyInfo(conn, anniversaryDailyInfo);
					if (result <= 0) {
						conn.rollback();
						log.info("弥补机会--添加每日领取表数据失败，userId=" + uid);
						return getFailRtn(-1, "弥补机会--添加每日领取表数据失败");
					}
				}
			}
			data = anniversaryQueryService.getAnniversaryDailyCount(uid, conn);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			return getFailRtn(-1, "失败");
		} finally {
			conn.close();
		}
		return getSuccRtn("成功", data);
	}

	/********************* public method **********************/

	/********************* private method **********************/

	/**
	 * 领取某日奖励奖品
	 * 
	 * @param conn
	 * @param uid
	 *            用户id
	 * @param dayNum
	 *            奖品类型
	 * @return null 领取成功,not null : 领取失败
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年9月1日 下午12:17:39
	 * @version 2016_Anniversary
	 */
	private String takeDayPrize(Connection conn, long uid, int dayNum)
			throws MySQLIntegrityConstraintViolationException, Exception {

		// 查询礼品信息
		List<Map<String, Object>> giftScheduleList = anniversaryGiftScheduleDao.queryAnniversaryGiftSchedule(conn,
				dayNum);

		if (null == giftScheduleList || giftScheduleList.size() <= 0) {
			log.error("没有当天奖品信息");
			return "领取失败";
		}
		Iterator<Map<String, Object>> giftScheduleIt = giftScheduleList.iterator();
		while (giftScheduleIt.hasNext()) {
			Map<String, Object> giftSchedule = (Map<String, Object>) giftScheduleIt.next();

			long takeRtn = -1; // code:领取奖品标识 // 0:成功,-1:失败,-2:领完了
			// 验证奖品信息
			if (null == giftSchedule || null == giftSchedule.get("id")) {
				log.error(String.format("不存在奖品信息%s", dayNum));
				return "不存在该奖品";
			}
			// 领取限量奖品 or 领取不限量奖品
			if ((Integer) giftSchedule.get("type") == 0) {
				// 不限量
				takeRtn = takeUnsizePrize(conn, uid, giftSchedule);
				if (takeRtn < 0) {
					// 不限量奖品 任何一个奖品领取失败就全部失败
					return "领取失败";
				}
			} else {
				// 限量
				// 更新发放数量
				takeRtn = takeSizePrize(conn, uid, giftSchedule);

				if (takeRtn >= 0) {
					// 限量奖品发送成功:更新发送数量并且跳出循环;
					break;
				} else if (takeRtn == -1) {
					return "领取失败";
				} else if (takeRtn == -2) {
					if (giftScheduleIt.hasNext()) {
						// 这个奖品被领完了,而且不是当前可领的最后一个奖品那么
						continue;
					} else {
						// 如果用户一个奖品都没有拿到,那么插入一条空title的数据到t_anniversary_gift_list表中
						long addRtn = anniversaryGiftListDao.addGiftList(conn, uid, dayNum, "",
								"用户" + uid + "领取" + dayNum + "日奖品失败,原因:奖品已全部领完");
						if (addRtn < 0) {
							log.error("添加领奖记录失败");
							return "添加领奖记录失败";
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 领取不限量奖品
	 * 
	 * @param uid
	 *            用户id
	 * @param giftSchedule
	 *            周年庆礼品
	 * @return 0:成功,-1:领取失败
	 * @author chenrui
	 * @date 2016年8月25日 下午8:07:10
	 * @version 2016_Anniversary
	 * @throws Exception
	 */
	private long takeUnsizePrize(Connection conn, long uid, Map<String, Object> giftSchedule) throws Exception {
		// 奖品名称
		String prizeName = (String) giftSchedule.get("title");
		if (StringUtils.isBlank(prizeName)) {
			log.error("奖品名称为空");
			return -1;
		}
		// 奖品内容
		String prizeContent = (String) giftSchedule.get("content");
		// 日期号
		int dayNum = (Integer) giftSchedule.get("day_num");
		// 领取虚拟礼品
		long takeVirtualRtn = takeVirtualPrize(conn, uid, dayNum, prizeName, prizeContent);
		if (takeVirtualRtn > 0) {
			log.info(dayNum + "日奖品" + prizeName + "是不限量虚拟物品;已发放完成.");
		} else if (takeVirtualRtn == -3) {
			log.info(dayNum + "日奖品" + prizeName + "是不限量实物奖品,开始发放");
			long updateRtn = 0; // 修改礼品状态成功标识 <1:不成功
			int count = 0; // 重试次数:因为并发原因导致的脏读/幻读可能导致修改状态失败所以设置重试次数
			while (updateRtn == 0 && count < 10) {
				// 查询不限量奖品
				Map<String, String> unSizePrize = anniversaryGiftUnsizeDao.queryUnTakeGiftByTitle(conn, prizeName);
				if (null == unSizePrize || null == unSizePrize.get("id")
						|| StringUtils.isBlank(unSizePrize.get("id"))) {
					log.error("不限量奖品[" + prizeName + "]已领完");
					// 奖品已领完跳出循环
					updateRtn = -1;
				} else {
					log.info("不限量奖品[" + prizeName + "]未领完");
					long id = Long.parseLong(unSizePrize.get("id"));
					// 修改礼品状态
					updateRtn = anniversaryGiftUnsizeDao.updateGiftTaked(conn, uid, id, dayNum);
					// 获取实物礼品内容
					prizeContent = unSizePrize.get("content");
				}
				count++;
			}

			if (updateRtn <= 0) {
				// 如果没有领到奖品,直接返回失败,不限量奖品可以随时补充
				log.error("添加领奖记录失败");
				return -1;
			}
		} else {
			return -1;
		}
		// 不限量礼品领取成功,发送短信/站内信
		if (dayNum < 100) {
			sendMessage(conn, uid, dayNum, prizeName, prizeContent);
		}
		return 1;
	}

	/**
	 * 领取限量礼品
	 * 
	 * @param conn
	 * @param uid
	 *            用户id
	 * @param giftSchedule
	 *            奖品信息
	 * @return 0:成功,-1:领取失败,-2:领完了
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年8月26日 下午8:00:36
	 * @version 2016_Anniversary
	 */
	private long takeSizePrize(Connection conn, long uid, Map<String, Object> giftSchedule) throws Exception {
		long updateRtn = anniversaryGiftScheduleDao.updateScheduleSendNum(conn, (Long) giftSchedule.get("id"));
		if (updateRtn <= 0) {
			log.error("更新领取数量失败:已发完");
			return -2;
		}
		// 日期号
		int dayNum = (Integer) giftSchedule.get("day_num");
		String prizeName = (String) giftSchedule.get("title"); // 礼品名称
		String prizeContent = (String) giftSchedule.get("content"); // 礼品描述

		// 领取虚拟礼品
		long takeVirtualRtn = takeVirtualPrize(conn, uid, dayNum, prizeName, prizeContent);
		if (takeVirtualRtn > 0) {
			log.info(dayNum + "日奖品" + prizeName + "是限量虚拟物品;已发放完成.");
		} else if (takeVirtualRtn == -3) {
			int count = 0; // 重试次数:因为并发原因导致的脏读/幻读可能导致修改状态失败所以设置重试次数
			long takePrizeRtn = 0;
			while (takePrizeRtn == 0 && count < 10) {
				// 1.查询奖品
				Map<String, String> sizePrize = anniversaryGiftSizeDao.queryUnTakeGift(conn, prizeName);
				if (null == sizePrize || null == sizePrize.get("id") || StringUtils.isBlank(sizePrize.get("id"))) {
					log.info("限量礼品[" + prizeName + "]已领完");
					return -2;
				}
				long id = Long.parseLong(sizePrize.get("id")); // id
				// 修改礼品状态
				takePrizeRtn = anniversaryGiftSizeDao.updateGiftTaked(conn, uid, id, dayNum);
				// 记录礼品内容
				prizeContent = sizePrize.get("content");
			}
			if (takePrizeRtn <= 0) {
				// 如果重试十次都没有领取成功,那么直接返回失败.
				return -1;
			}
		} else {
			return -1;
		}
		// 限量礼品领取成功,发送短信/站内信
		if (dayNum < 100) {
			sendMessage(conn, uid, dayNum, prizeName, prizeContent);
		}
		return 1;
	}

	/**
	 * 发放虚拟物品(需要客服联系用户发放的):现金卷,915大礼包,流量,能量,马达加斯加
	 * 
	 * @param conn
	 * @param uid
	 *            用户id
	 * @param dayNum
	 *            日期号
	 * @param prizeName
	 *            奖品名称
	 * @param prizeContent
	 *            奖品内容
	 * @return
	 * @throws MySQLIntegrityConstraintViolationException
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年9月1日 下午2:12:56
	 * @version 2016_Anniversary
	 */
	private long takeVirtualPrize(Connection conn, long uid, long dayNum, String prizeName, String prizeContent)
			throws MySQLIntegrityConstraintViolationException, Exception {
		if (prizeName.contains(PRIZE_NAME_COUPON)) {
			// 奖品是现金券

			if (StringUtils.isBlank(prizeContent)) {
				log.error("现金券类型为空");
				return -1;
			}
			// 发放现金券
			long couponType = Long.parseLong(prizeContent);
			long addCouponRtn = userCouponService.addCouponToUserByType(conn, uid, couponType);
			if (addCouponRtn <= 0) {
				log.error("发放现金券失败");
				return -1;
			}
			// 记录领奖数据
			long addRtn = anniversaryGiftListDao.addGiftList(conn, uid, dayNum, prizeName, prizeContent);
			if (addRtn <= 0) {
				log.error("添加领奖记录失败");
				return -1;
			}

		} else if (prizeName.contains(PRIZE_NAME_ENERGY)) {
			// 奖品是能量

			if (StringUtils.isBlank(prizeContent)) {
				log.error("能量值为空");
				return -1;
			}
			// 添加能量
			long addRateRtn = anniversaryRateService.addAnniversaryRate(conn, uid, new BigDecimal(0), 0, false,
					new BigDecimal(prizeContent), 2);
			if (addRateRtn <= 0) {
				log.error("添加能量失败");
				return -1;
			}
			// 记录领奖数据
			long addRtn = anniversaryGiftListDao.addGiftList(conn, uid, dayNum, prizeName, prizeContent);
			if (addRtn <= 0) {
				log.error("添加领奖记录失败");
				return -1;
			}

		} else if (prizeName.contains(PRIZE_NAME_915BAG)) {
			// 915大礼包
			if (StringUtils.isBlank(prizeContent)) {
				log.error("礼包中不包含现金券");
				return -1;
			}
			// 发放现金券
			String[] couponTypeList = prizeContent.split(",");
			if (null == couponTypeList || couponTypeList.length < 1) {
				log.error("礼包中不包含现金券");
				return -1;
			}

			for (int i = 0; i < couponTypeList.length; i++) {
				// 现金券类型
				String couponType = couponTypeList[i];
				if (StringUtils.isBlank(couponType)) {
					continue;
				}
				long addCouponRtn = userCouponService.addCouponToUserByType(conn, uid, Long.parseLong(couponType));
				if (addCouponRtn <= 0) {
					log.error("发放现金券失败");
					return -1;
				}
			}
			// 记录领奖数据
			long addRtn = anniversaryGiftListDao.addGiftList(conn, uid, dayNum, prizeName, prizeContent);
			if (addRtn <= 0) {
				log.error("添加领奖记录失败");
				return -1;
			}
		} else if (prizeName.contains(PRIZE_NAME_FLOW) || prizeName.contains(PRIZE_NAME_MADAGASCAR)) {
			// 流量||马达加斯加 记录领奖数据
			long addRtn = anniversaryGiftListDao.addGiftList(conn, uid, dayNum, prizeName, prizeContent);
			if (addRtn <= 0) {
				log.error("添加领奖记录失败" + prizeName);
				return -1;
			}
		} else {
			// 奖品不是虚拟物品
			return -3;
		}
		return 1;
	}

	/**
	 * 添加(当天领取)或修改(补签领取)签到记录
	 * 
	 * @param conn
	 * @param uid
	 *            用户id
	 * @param dayNum
	 *            日期号
	 * @return
	 * @throws SQLException
	 * @throws DataException
	 * @author chenrui
	 * @date 2016年8月31日 下午3:16:27
	 * @version 2016_Anniversary
	 */
	private long insertOrUpdateDaily(Connection conn, long uid, int dayNum) throws SQLException, DataException {
		Map<String, String> userDaily = anniversaryDailyDao.queryUserDaily(conn, uid, dayNum);
		if (null == userDaily || null == userDaily.get("id")) {
			// 如果不存在数据,直接插入
			SimpleDateFormat sf = new SimpleDateFormat(UtilDate.simple);
			Map<String, String> anniversaryDailyInfo = new HashMap<String, String>();
			anniversaryDailyInfo.put("userid", uid + "");
			anniversaryDailyInfo.put("day_num", dayNum + "");
			anniversaryDailyInfo.put("status", "2");
			anniversaryDailyInfo.put("receive_time", "'" + sf.format(new Date()) + "'");
			return anniversaryDailyDao.insertAnniversaryDailyInfo(conn, anniversaryDailyInfo);
		} else {
			// 存在补签数据,修改状态为3:已补签
			return anniversaryDailyDao.updateDailyStatusToTaked(conn, uid, dayNum);
		}
	}

	/**
	 * 礼品领取成功发送短信/站内信
	 * 
	 * @param conn
	 * @param uid
	 *            用户id
	 * @param dayNum
	 *            日期号
	 * @param prizeName
	 *            礼品名称
	 * @param prizeContent
	 *            礼品内容
	 * @param sendType
	 *            1:只发送站内信,2:只发送短信,3:短信站内信都发送
	 * @author chenrui
	 * @date 2016年9月6日 上午11:17:33
	 * @version 2016_Anniversary
	 */
	private void sendMessage(Connection conn, long uid, long dayNum, String prizeName, String prizeContent) {
		int sendType = 3; // 1: 只发送站内信,2:只发送短信,3:短信+站内信
		String mailSendContent = "";
		if (dayNum < 100) {
			mailSendContent = String.format("“精彩2周年，高能焕新颜”，恭喜您获得［%s］奖励！周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。", prizeName);
			// 如果是每日礼品,且礼品是现金卷或能量,只发站内信
			if (prizeName.trim().contains(PRIZE_NAME_915BAG) || prizeName.trim().contains(PRIZE_NAME_COUPON)
					|| prizeName.trim().contains(PRIZE_NAME_ENERGY)) {
				// 现金券和能量只发站内信
				mailSendContent = String.format("“精彩2周年，高能焕新颜”，恭喜您获得［%s］奖励！周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。", prizeName);
				sendType = 1;
			}
			if(prizeName.trim().contains(PRIZE_NAME_AIQIYI) ){
				mailSendContent = String.format("“精彩2周年，高能焕新颜”，恭喜您获得［爱奇艺月卡］奖励！兑换码：%s ，请登录 www.iqiyi.com 查看规则并使用，有效期截至2016年12月30日。周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。", prizeContent);
			}
			if(prizeName.trim().contains(PRIZE_NAME_HAAGENDAZS) ){
				mailSendContent = String.format("“精彩2周年，高能焕新颜”，恭喜您获得［哈根达斯单球杯］奖励！兑换码：%s，请在中国大陆地区哈根达斯专卖店使用(特殊店除外)，有效期截至2017年03月07日24时，规则详情可登录 www.haagendazs.com.cn查看。周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。", prizeContent);
			}
			if(prizeName.trim().contains(PRIZE_NAME_CINEMA) ){
				mailSendContent = String.format("“精彩2周年，高能焕新颜”，恭喜您获得［星美观影券］奖励！兑换码：%s，有效期截至2016年12月09日。凭短信在影院售票处兑换，全国237家星美影院直营店2D／3D影片通兑。周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。", prizeContent);
			}
		} else {
			if (dayNum == 100) {
				mailSendContent = "“精彩2周年，高能焕新颜”，恭喜您获得［200元礼包］奖励！奖品为多美贷100元现金券、1G流量包，请笑纳。周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。";
			}
			if (dayNum == 101) {
				mailSendContent = "“精彩2周年，高能焕新颜”，恭喜您获得［500元礼包］奖励！奖品为多美贷100元现金券、多美贷300元现金券、2G流量包，请笑纳。周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。";
			}
			if (dayNum == 102) {
				mailSendContent = "“精彩2周年，高能焕新颜”，恭喜您获得［终极大奖］奖励！奖品为马达加斯加单人游，请笑纳。周年庆期间登录多美贷官网或APP，大奖不断惊喜连连！详情致电400－885－7027。";
			}
		}
		try {
			if (sendType == 3 || sendType == 1) {
				sendMessageService.mailSend("2016周年庆礼品领取成功", mailSendContent, uid);
			}
			if (sendType == 3 || sendType == 2) {
				Map<String, String> user = userDao.queryUserById(conn, uid);
				// 发送短信
				sendMessageService.send(mailSendContent, user.get("mobilePhone"), uid, "01");
			}
		} catch (Exception e) {
			log.error(String.format("[2016周年庆领取奖品]领取奖品成功发送短信站内信失败;用户id:%d奖品日期号:%d,奖品名称:%s,奖品内容:%s", uid, dayNum,
					prizeName, prizeContent));
		}
	}

	/**
	 * 构造提取的返回数据
	 * 
	 * @param conn
	 * @param uid
	 * @return
	 * @throws Exception
	 * @author chenrui
	 * @date 2016年9月1日 上午11:52:45
	 * @version 2016_Anniversary
	 */
	private Map<String, Object> getTakeRtnData(Connection conn, long uid, int dayNum) throws Exception {
		// 构造返回数据
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> _temp = anniversaryQueryService.getAnniversaryDailyCount(uid, conn); // 存储用户礼物相关次数
		// 错过
		data.put("missCount",
				null == _temp || StringUtils.isBlank(_temp.get("missCount")) ? 0 : _temp.get("missCount"));
		// 登陆次数
		data.put("loginCount",
				null == _temp || StringUtils.isBlank(_temp.get("loginCount")) ? 0 : _temp.get("loginCount"));
		// 弥补机会
		data.put("chanceCount",
				null == _temp || StringUtils.isBlank(_temp.get("chanceCount")) ? 0 : _temp.get("chanceCount"));
		// 可领取次数
		data.put("notGiftCount",
				null == _temp || StringUtils.isBlank(_temp.get("notGiftCount")) ? 0 : _temp.get("notGiftCount"));
		// 已领取天数
		data.put("getGiftCount",
				null == _temp || StringUtils.isBlank(_temp.get("getGiftCount")) ? 0 : _temp.get("getGiftCount"));
		// 我获得的奖品列表
		List<Map<String, String>> giftList = anniversaryQueryService.getAnniversaryAnnouncemenGetGiftList(uid, conn);
		data.put("giftList", null == giftList ? new ArrayList<Map<String, String>>() : giftList);
		// // 查询用户当前领取的奖品
		// List<Map<String, Object>> currentTakeGift =
		// anniversaryGiftListDao.queryGiftListByDaynum(conn, uid, dayNum);
		// if(currentTakeGift == null){
		// currentTakeGift = new ArrayList<Map<String, Object>>();
		// }
		// data.put("currentTakeGift", currentTakeGift);
		return data;
	}

	/********************* private method **********************/

}
