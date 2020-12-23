package com.bdjbd.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className RegionUtil
 * @description TODO
 * @date 2019/9/19
 **/
public final class RegionUtil {

    /**
     * 判断当前定位点是否在指定区域内
     * @param gpsLng GPS 经度
     * @param gpsLat GPS 纬度
     * @param range 高德地图区域
     * @return
     */
    public static Boolean isInRegionGPS(String gpsLng, String gpsLat, String range){
        double[] gdPoint = GPSUtil.gps84_To_Gcj02(Double.valueOf(gpsLat), Double.valueOf(gpsLng));
        Point2D.Double point = new Point2D.Double(gdPoint[1], gdPoint[0]);//当前位置坐标
        List<Point2D.Double> points  = getPoints(range);//将围栏范围转换成List<Point2D.Double>坐标点
        return checkWithJdkPolygon(point, points);
    }

    /**
     * 判断当前定位点是否在指定区域内
     * @param gpsLng GPS 经度
     * @param gpsLat GPS 纬度
     * @param range 高德地图区域
     * @return
     */
    public static Boolean isInRegion(String gpsLng, String gpsLat, String range){
        Point2D.Double point = new Point2D.Double(Double.valueOf(gpsLng), Double.valueOf(gpsLat));//当前位置坐标
        List<Point2D.Double> points  = getPoints(range);//将围栏范围转换成List<Point2D.Double>坐标点
        return checkWithJdkPolygon(point, points);
    }

    /**
     * 将围栏范围转换成List<Point2D.Double>坐标点
     *
     * @param range
     */
    public static List<Point2D.Double> getPoints(String range) {
        List<Point2D.Double> points = new ArrayList<>();
        JSONArray array = JSON.parseArray(range);
        for(int i = 0; i < array.size(); i++){
            JSONArray item = (JSONArray)array.get(i);
            points.add(new Point2D.Double(item.getDouble(0), item.getDouble(1)));
        }
        return points;
    }

    /**
     * 判断当前位置是否在电子围栏中
     *
     * @param point   当前坐标
     * @param polygon 电子围栏坐标集合
     * @return true:在电子围栏中 false:不在电子围栏中
     */
    public static boolean checkWithJdkPolygon(Point2D.Double point,
                                              List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();

        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            p.lineTo(d.x, d.y);
        }

        p.lineTo(first.x, first.y);
        p.closePath();
        return p.contains(point);
    }
}
