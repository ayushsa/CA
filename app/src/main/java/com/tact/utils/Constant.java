package com.tact.utils;


import android.content.Context;

import com.bumptech.glide.RequestManager;
import com.tact.kumbhca.R;

/**
 * Created by admin on 28/06/16.
 */
public class Constant {
    public static final String KEY_APPBLOCK = "app_block";
    public static final String MOBILE_SELECT_POLITICAL_PARTY_ULB = "MobileSelectPoliticalPartyULB";
    public static final String LOCAL_DATE_FORMAT = "dd-MMM-yyyy";
    public static final String SERVER_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss";
    public static final String METHOD_GET_ELECTION_SYMBOL = "GetElectionSymbol";
    public static final String BODY_ID = "BodyId";
    public static final String CATEGORY_ID = "CategoryId";
    public static final String BODY_SUBTYPE_ID = "BodySubTypeId";
    public static final String POST_ID = "PostId";
    public static final String MOBILE_SELECT_ELECTION_SYMBOL = "MobileSelectElectionSymbol";
    public static final String ULB_PRITYPE = "ULB_PRIType";
    public static final String METHOD_GET_DISTRICT_PLANNING_COMMITTEE_STATS = "GetDistrictPlanningCommitteeStats";
    public static final String IS_ON_DASHBOARD = "dashboard";

    public static Context con = Support.con;

    public static final String TEMP_SHARE_IMAGE = "temp.jpg";
    public static final String RESULT_CANCEL = "Cancel";

    public static int DEFAULT_PROGRESS_COLOR = R.color.app_color;

    // API TYPE
    public static final short WEB_SERIVCE = 1;
    public static final short POST = 0;

    public static final String Id = "Id";

    public static final String PREF_NAME = "SEC";

    public static final String METHOD_POLITICAL_PARTY_ULB = "GetMobileSelectPoliticalPartyULB";
    public static final String METHOD_PHOTO_URL = "http://sec.up.nic.in/site/Event_Photo/";
    public static final String METHOD_GETDISTRICT = "GetDistrict";
    public static final String METHOD_GET_PREVIOUS_DISTRICT = "GetPreviousDistrict";


    public static final String METHOD_GETULBTYPE = "GetULBType";
    public static final String METHOD_GETULB = "GetULB";
    public static final String METHOD_GETULBWARD = "GetULBWard";
    public static final String METHOD_GETULBVOTER = "GetULBVoter";
    public static final String METHOD_GETPRIVOTER = "GetPRIVoter";
    public static final String METHOD_GETNEWSHEADLINE = "GetnewsHeadline";
    public static final String METHOD_GETCIRCULAR = "GetCircular";

    public static final String METHOD_GETTENDERS = "GetTenders";
    public static final String METHOD_GETPRESSRELEASE = "GetPressRelease";


    public static final String METHOD_GETPHOTOALBUM = "GetEvents";
    public static final String METHOD_GETPHOTOGALLERY = "GetEventDetail";
    public static final String METHOD_GETMEDIAALBUM = "GetMedia";
    public static final String METHOD_GETMEDIAGALLERY = "GetMediaDetail";
    public static final String METHOD_GETBLOCK = "GetBlock";
    public static final String METHOD_GETGP = "GetGP";
    public static final String METHOD_GETPOLLINGPLACE = "GetPollingStationULB";
    public static final String METHOD_GETBLOSO = "GetBLOAndSUAndSO";
    public static final String METHOD_GETPRIVOTINGRESULT = "GetPRIVotingResult";
    public static final String METHOD_GETZPWARD = "GetZPWard";
    public static final String METHOD_GETKPWard = "GetKPWard";
    public static final String METHOD_GETPRIVOTINGRESULTGPWARD = "GetPRIVotingResultGPWard";
    public static final String METHOD_GETPOLLING_CENTER = "GetPollingCenter";
    public static final String METHOD_GIS_SEARCH = "GetGISSearch";
    public static final String METHOD_GETPOLLINGPERCENTAGE = "GetPollingPercentageGPPGPM";
    public static final String METHOD_GET_IMPORTANT_FORMS = "GetImportantForms";
    public static final String METHOD_GET_PRISTATS = "GePRIStats";
    public static final String METHOD_GET_PUBLICATIONS = "GetPublications";
    public static final String METHOD_PRIRESERVATION = "GetMobileSearchReservation";
    public static final String METHOD_GETWINNERLIST = "GetWinnerList";
    public static final String METHOD_GETACTANDRULE = "GetActAndRule";

    public static final String METHOD_GET_MOBILE_SELECT_ULBSTATS = "GetMobileSelectULBStats";


    public static final String METHOD_INSERT_REGISTER_DEVICE = "InsertRegisterDevice";

    public static final String METHOD_GET_NOTIFICATION_ON_DATA = "GetNotificationOnData";

    public static final String METHOD_GET_DISTRICT_OFFICE = "GetDistrictOffice";
    public static final String METHOD_GET_LIST_OF_OFFICERS = "GetListOfOfficers";

    public static final String FONT_PATH = "fonts/RobotoRegular.ttf";

    public static final String BASE_FOLDER_PATH = "SEC";
    public static final String METHOD_CIRCULAR_PDF = "http://sec.up.nic.in/site/writedata/";
    public static final String FILE_FORMAT = ".pdf";
    public static final String KEY_CURRENT_INDEX = "curr_index";
    public static final String DATA = "Data";
    public static final String METHOD_PHOTO_BASEURL = "http://sec.up.nic.in/site/";

    public static RequestManager Glide;

    public static final String ERROR_MSG_INTERNET = "";
    //con.getString(R.string.ERROR_MSG_INTERNET);

    public static final String CONTENT_H = "ContentH";

    public static final String YOUTUBE_API1 = "https://gdata.youtube.com/feeds/api/users/LLilj5frv_Ud3DMLprdAliog/uploads?max-results=10&start-index=";
    public static final String YOUTUBE_API2 = "&prettyprint=true&fields=entry(title,media:group(media:player,media:thumbnail))&alt=json";
    public static final String URL = "url";

    public static final String PLAYLIST_FOR_VIDEO_GALLERY_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=UUilj5frv_Ud3DMLprdAliog&key=AIzaSyCecEVJFkTgrVXs1S22SX0LBsDRfv3txgg&maxcount=20";

    public static final String INPUT_TRANSLATER_API1 = " https://inputtools.google.com/request?text=";

    public static final String INPUT_TRANSLATER_API2 = "&itc=hi-t-i0-und&num=13&cp=0&cs=1&ie=utf-8&oe=utf-8&app=demopage";


    public static final String KEY_DISTRICT_ID = "DistrictId";
    public static final String KEY_POST_ID = "PostId";
    public static final String KEY_LIST = "List";

    public static final String KEY_DEVICE_ID = "DeviceId";
    public static final String KEY_NOTIFICATION_ID = "NotificationId";
    public static final String KEY_TYPE = "Type";
    public static final String KEY_USER_ID = "UserId";

    public static final String GOOGLE_SENDER_ID = "316324019380";
    public static final String NOTIFICATION_ID = "NotificationId";

    public static final String DEVICE_ID = "DeviceId";
    public static final String DEVICE_TYPE = "DeviceType";

    public static final String RESULT = "Result";
    public static final String RESULT_OK = "1";



    public static final String REG_TO_SERVER = "isRegToServers";

    public static final String ERROR_MSG_CHECK_INTERNET = con.getString(R.string.ERROR_MSG_CHECK_INTERNET);

    public static final String KEY_ISLOGIN = "isLogIn";

    public static final String DASHBOARD_DATA = "DashboardData";
}
