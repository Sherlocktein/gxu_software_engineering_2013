/*
 * Copyright 2013 Department of Computer Science and Technology, Guangxi University
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package gxu.software_engineering.shen10.market.service;

import gxu.software_engineering.shen10.market.entity.Item;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 物品服务接口
 * 
 * @author longkai(龙凯)
 * @email  im.longkai@gmail.com
 */
public interface ItemService {

	/**
	 * 发布一个新物品。
	 * @return 若创建成功，返回成功创建的，否则抛出异常。
	 */
	Item create(@NotNull Item item, @Min(1) long categoryId, @Min(1) long sellerId);

	/**
	 * 修改一个物品。
	 * @return 返回修改成功后的物品，否则抛出异常。
	 */
	Item modify(@NotNull Item item, @Min(1) long cid, @Min(1) long uid);
	
	/**
	 * 修正一个物品的信息。
	 * @return 返回修改成功后的物品，否则抛出异常。
	 */
	@NotNull(message = "对不起，您所要查早的物品不存在！")
	Item alter(@Min(1) long id, float price, String name, String desc, String extra);

	/**
	 * 物品所属人将物品标注为关闭状态，或者将已经关闭的物品置为打开状态（可以出售）。
	 * @param closed 是否关闭。
	 * @param user 提出申请的卖家。
	 * @param itemId 想要关闭或者打开的物品的标识。
	 */
	Item close(boolean close, @Min(1) long uid, @Min(1) long itemId);

	/**
	 * 查看物品详细说明，若不存在，或者被管理员锁住，则会抛出异常。
	 * @param 物品id标识。
	 */
	@NotNull(message = "对不起，您所要查早的物品不存在！")
	Item detail(@Min(1) long itemId);

	/**
	 * 将物品屏蔽或者解开屏蔽（适用于管理员）。
	 * @param id
	 * @param blocked
	 */
	@NotNull(message = "对不起，您所要查早的物品不存在！")
	Item block(@Min(1) long id, boolean blocked);

	/**
	 * 查看最新的物品列表。
	 */
	List<Item> latest(@Min(1) @Max(50) int count);

	/**
	 * 查看物品的列表。
	 * @param lastItemId 上一个物品的id，为0表示刷新。
	 */
	List<Item> list(@Min(0) long lastItemId, @Min(1) @Max(50) int count);

	/**
	 * 返回系统所有的物品数。
	 * @param deal 是否卖出。
	 * @return 若deal为true，那么返回所有的，否则返回待售的。
	 */
	long size(boolean deal);

	/**
	 * 某个卖家的物品数。
	 * @param deal 是否已经卖出
	 * @return 若deal为true，那么返回所有的，否则返回待售的。
	 */
	long size(@Min(1) long userId, boolean deal);

	/**
	 * 返回某个卖家的物品。
	 * @param lastItemId 查看更多的物品的偏移量，为0表示刷新。
	 * @return 若deal为true，那么返回的是所有的，否则返回待售的。
	 */
	List<Item> list(@Min(1) long userId, @Min(1) @Max(50) int count, boolean deal, @Min(0) long lastItemId);
	
	/**
	 * 返回某个卖家关闭交易的物品列表。
	 * @param lastItemId 查看更多的物品的偏移量，为0表示刷新。
	 */
	List<Item> closed(@Min(1) long userId, @Min(1) @Max(50) int count, @Min(0) long lastItemId);

	/**
	 * 返回某个类别下的待售物品。
	 * @param lastItemId 查看更多的物品的偏移量，为0表示刷新。
	 */
	List<Item> list(@Min(1) long categoryId, @Min(1) @Max(50) int count, @Min(0) long lastItemId);

	/**
	 * 返回某个类别下的待售物品数。
	 */
	long size(@Min(1) long categoryId);

	/**
	 * 返回最热门的物品列表。
	 */
	List<Item> hot(@Min(1) @Max(50) int count);
	
	/**
	 * 同步
	 * @param lastSyncMills
	 */
	List<Item> sync(@Min(1) long lastSyncMills, @Min(1) @Max(200) int count);

	/**
	 * 物品相关的搜索。
	 * @param name
	 * @param minPrice
	 * @param maxPrice
	 * @param lastId (用于加载更多)
	 * @param count
	 */
	Map<String, Object> search(String name, float minPrice, float maxPrice,
			long lastId, int count);
	
}
