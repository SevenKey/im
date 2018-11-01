package version;

import org.apache.commons.lang.StringUtils;

public class Version {

    public boolean versionIsInSection(int version, int versionStart, int versionEnd) {
        return version >= versionStart && version <= versionEnd;
    }

    public static boolean checkAppVersion(String currentVersion, String targetVersion) {
        if (currentVersion == null) {
            return false;
        }

        return parseVersion2Int(currentVersion) >= parseVersion2Int(targetVersion);
    }

    public static int parseVersion2Int(String version) {
        try {

            if (StringUtils.isEmpty(version)) {
                return 0;
            }

            String versionStrip = version.replaceAll("\\.", "");
            if (versionStrip.length() < 3) {
                versionStrip = versionStrip + "0";
            }
            return Integer.parseInt(versionStrip);
        } catch (Exception e) {
        }
        return 0;
    }

    public boolean compare(String versionName, String setVersionName) {
        String[] versionElement = versionName.split("\\.");
        int mainVersion = Integer.parseInt(versionElement[0]);
        int secondVersion = Integer.parseInt(versionElement[1]);

        String[] setVersionNameArr = setVersionName.split("\\.");
        int setMainVersion = Integer.parseInt(setVersionNameArr[0]);
        int setSecondVersion = Integer.parseInt(setVersionNameArr[1]);
        int setThirdVersion = Integer.parseInt(setVersionNameArr[2]);

        if (mainVersion > setMainVersion) {
            return true;
        } else if (mainVersion < setMainVersion) {
            return false;
        }
        if (secondVersion > setSecondVersion) {
            return true;
        } else if (secondVersion < setSecondVersion) {
            return false;
        }
        int thirdVersion = 0;
        if (versionElement.length >= 3) {
            thirdVersion = Integer.parseInt(versionElement[2]);

        }
        if (thirdVersion >= setThirdVersion) {
            return true;
        }
        return false;
    }

    public boolean check840(String versionName) {
        String[] versionElement = versionName.split("\\.");
        int mainVersion = Integer.parseInt(versionElement[0]);
        int secondVersion = Integer.parseInt(versionElement[1]);
        if (mainVersion > 8) {
            return true;
        }
        if (mainVersion == 8 && secondVersion > 4) {
            return true;
        }
        int thirdVersion = 0;
        if (versionElement.length >= 3) {
            thirdVersion = Integer.parseInt(versionElement[2]);
        }
        if (mainVersion == 8 && secondVersion == 4 && thirdVersion >= 0) {
            return true;
        }
        return false;
    }

    public int getDPVersion(String version) {
        String versionTmp = version.replace(".", "");
        if (versionTmp.length() < 3) {
            versionTmp += "0";
        }
        return Integer.valueOf(versionTmp);
    }
}
