package com.easy.auth;

public class Test {
    // 地球平均半径，单位：米
    private static final double EARTH_RADIUS = 6371000;

    public static void main(String[] args) {
        // 围栏中心点坐标（纬度, 经度）
        double fenceLatitude = 39.9042;
        double fenceLongitude = 116.4074;
        // 围栏半径，单位：米
        double radius = 500;

        // 设备位置坐标（纬度, 经度）
        double deviceLatitude = 39.9102;
        double deviceLongitude = 116.4174;

        if (isInCircle(fenceLatitude, fenceLongitude, radius, deviceLatitude, deviceLongitude)) {
            System.out.println("设备在围栏内");
        } else {
            System.out.println("设备已走出围栏");
        }
    }

    /**
     * 判断给定的坐标是否位于以指定经纬度为中心、指定半径的圆圈内。
     *
     * @param centerLat 圆心纬度
     * @param centerLng 圆心经度
     * @param radius    半径，单位：米
     * @param pointLat  点的纬度
     * @param pointLng  点的经度
     * @return 如果点在圆内返回true，否则返回false
     */
    public static boolean isInCircle(double centerLat, double centerLng, double radius, double pointLat, double pointLng) {
        double distance = calculateDistance(centerLat, centerLng, pointLat, pointLng);
        return distance <= radius;
    }

    /**
     * 计算两点之间的距离，使用Haversine公式。
     *
     * @param lat1 第一点的纬度
     * @param lng1 第一点的经度
     * @param lat2 第二点的纬度
     * @param lng2 第二点的经度
     * @return 两点之间的距离，单位：米
     */
    private static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lngDistance = Math.toRadians(lng2 - lng1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // 距离，单位：米
    }
}
