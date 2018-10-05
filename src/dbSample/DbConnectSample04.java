package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectSample04 {

    public static void main(String[] args) {
        // データベース接続と結果取得のための変数
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.jdbc.Driver");

            // 2. DBと接続する
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/world?useSSL=false",
                "root",
                "Xwareadmin1234"
            );// "password"の部分は，ご自身でrootユーザーに設定したものを記載してください。

            // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            pstmt = con.prepareStatement("select * from country where Name = ?");

            // 4, 5. Select文の実行と結果を格納／代入
            String name = "' or 'a' = 'a";
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            // 6. 結果を表示する
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getInt("Population"));
            }

        } catch (SQLException e) {
            // DBとの処理で何らかのエラーがあった場合の例外
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            // JDBCドライバを読み込めないエラーがあった場合の例外
            e.printStackTrace();

        } finally {
            // 7. 接続を閉じる
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}