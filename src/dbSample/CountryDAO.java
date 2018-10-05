package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    // データベース接続と結果取得のための変数
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Country> getCountryFromName(String name) {
        // メソッドの結果として返すリスト
        List<Country> results = new ArrayList<Country>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            this.getConnecton();

            // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            pstmt = con.prepareStatement("select * from country where Name = ?");

            // 4, 5. Select文の実行と結果を格納／代入
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            // 6. 結果を表示する
            while (rs.next()) {
                // 1件ずつCountryオブジェクトを生成して結果を詰める
                Country country = new Country();
                country.setName(rs.getString("Name"));
                country.setPopulation(rs.getInt("Population"));

                // リストに追加
                results.add(country);
            }
        } catch (SQLException e) {
            // DBとの処理で何らかのエラーがあった場合の例外
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // DBとの処理で何らかのエラーがあった場合の例外
            e.printStackTrace();
        } finally {
            this.close();
        }

        return results;
    }

    public void getConnecton() throws SQLException, ClassNotFoundException {
        // 1. ドライバのクラスをJava上で読み込む
        Class.forName("com.mysql.jdbc.Driver");

        // 2. DBと接続する
        con = DriverManager.getConnection(
            "jdbc:mysql://localhost/world?useSSL=false",
            "root",
            "Xwareadmin1234"
        );// "password"の部分は，ご自身でrootユーザーに設定したものを記載してください。
    }

    private void close() {
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