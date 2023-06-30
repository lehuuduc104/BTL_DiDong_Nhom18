package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ArrayList<DataModel> recyclerDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh sách phim");


        recyclerView=findViewById(R.id.movie_list_recycler_view);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new DataModel("Avengers", R.drawable.avengers,"Biệt đội siêu anh hùng (hay Avengers: Biệt đội siêu anh hùng, tựa gốc tiếng Anh: The Avengers) là một bộ phim siêu anh hùng của Mỹ công chiếu vào năm 2012 được xây dựng dựa trên nguyên mẫu các thành viên của biệt đội siêu anh hùng Avengers của Marvel Comics, do Marvel Studios sản xuất và Walt Disney Studios Motion Pictures phân phối phát hành. Đây là bộ phim thứ 6 kết thúc Giai đoạn 1 trong Vũ trụ Điện ảnh Marvel (MCU). Bộ phim do Joss Whedon viết kịch bản kiêm đạo diễn, với sự tham gia của các diễn viên chính bao gồm Robert Downey, Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner trong vai các thành viên của đội Avengers, cùng với Tom Hiddleston, Clark Gregg, Cobie Smulders, Stellan Skarsgård và Samuel L. Jackson. Trong The Avengers, Nick Fury, người điều hành của tổ chức gìn giữ hòa bình S.H.I.E.L.D., chiêu mộ Tony Stark, Bruce Banner, Thor, và Steve Rogers để để thành lập một đội có khả năng ngăn chặn em trai của Thor, Loki chinh phục Trái đất.\n" +
                "\n" +
                "Quá trình thực hiện The Avengers bắt đầu khi Marvel Studios nhận được khoản vay từ Merrill Lynch vào tháng 4 năm 2005. Sau thành công của Người Sắt vào tháng 5 năm 2008, Marvel tuyên bố The Avengers sẽ được ra mắt vào tháng 7 năm 2011. Với việc diễn viên Scarlett Johansson ký hợp đồng vào tháng 3 năm 2009, bộ phim bị dời lại đến năm 2012. Whedon bắt đầu vào tháng 4 năm 2010 và đã viết lại kịch bản ban đầu của Zak Penn. Quá trình sản xuất bắt đầu vào tháng 4 năm 2010 ở Albuquerque, New Mexico, trước khi chuyển đến Cleveland, Ohio vào tháng 8 năm 2011 và thành phố New York vào tháng 9 năm 2011. Bộ phim được chuyển thành định dạng 3D vào cuối quá trình sản xuất.\n" +
                "\n" +
                "Biệt đội siêu anh hùng được công chiếu vào ngày 11/04/2012 tại El Capitan Theatre Hollywood, California. Phim nhận được nhiều nhận xét tích cực từ hầu hết những nhà phê bình phim, đồng thời cũng thiết lập nhiều kỷ lục phòng vé, bao gồm phim có doanh thu mở màn ấn tượng nhất tại Bắc Mỹ và phim cán mốc 1 tỷ đô nhanh nhất trên toàn thế giới. Với tổng doanh thu đạt hơn 1.46 tỷ đô la, Biệt đội siêu anh hùng cũng trở thành phim đứng thứ 3 trong danh sách những phim ăn khách nhất của điện ảnh Hoa Kỳ. Phim đã được lên kế hoạch phát hành trên đĩa Blu-ray và DVD vào ngày 25 tháng 9 năm 2012. Các phần tiếp theo của phim bao gồm Avengers: Đế chế Ultron, Avengers: Cuộc chiến vô cực và Avengers: Hồi kết lần lượt phát hành vào các năm 2015, 2018 và 2019."));
        recyclerDataArrayList.add(new DataModel("Captain Marvel", R.drawable.captain_marvel,"Đại uý Marvel (tựa gốc tiếng Anh: Captain Marvel) là một phim siêu anh hùng ra mắt năm 2019 của Mỹ dựa trên nhân vật Carol Danvers của truyện tranh Marvel. Được sản xuất bởi Marvel Studios và được phân phối bởi Walt Disney Studios Motion Pictures, tác phẩm là phim thứ 21 của Vũ trụ Điện ảnh Marvel (MCU). Phim được sáng tác và đạo diễn bởi Anna Boden và Ryan Fleck, với Geneva Robertson-Dworet và Jac Schaeffer cũng đóng góp cho kịch bản. Dàn diễn viên gồm Brie Larson đóng vai Carol Danvers, cùng với Samuel L. Jackson, Ben Mendelsohn, Djimon Hounsou, Lee Pace, Lashana Lynch, Gemma Chan, Annette Bening, Clark Gregg và Jude Law.[5] Lấy bối cảnh năm 1995,[6] phim kể về con đường trở thành siêu anh hùng mạnh mẽ nhất vũ trụ của Carol Danvers. Bên cạnh đó, trận chiến ảnh hưởng đến toàn vũ trụ giữa tộc Kree và tộc Skrull đã lan đến Trái Đất.[5][7]\n" +
                "\n" +
                "Được phát triển vào tháng 5 năm 2013 và chính thức được công bố vào tháng 10 năm 2014, tác phẩm là phim siêu anh hùng Marvel đầu tiên có nhân vật chính là nữ.[8][9] Nicole Perlman và Meg LeFauve được thuê để sáng tác kịch bản vào tháng 4 năm 2015. Nội dung phim mượn những yếu tố từ loạt truyện Chiến tranh Kree–Skrull năm 1971 của Roy Thomas.[10][11] Larson được công bố sẽ đóng vai Carol Danvers tại sự kiện San Diego Comic-Con năm 2016.[12][13] Boden và Fleck được giao việc đạo diễn phim vào tháng 4 năm 2017. Robertson-Dworet nhanh chóng đảm nhận việc sáng tác kịch bản, với những diễn viên còn lại được thêm vào dàn diễn viên trước khi phim khởi quay. Quá trình quay phim bắt đầu vào tháng 1 năm 2018,[14][15] với nhiếp ảnh chính bắt đầu vào tháng 3 năm 2018 tại California và kết thúc vào tháng 7 năm 2018 tại Louisiana.[16][17] Trong quá trình hậu kỳ, Jackson và Gregg, những diễn viên đã tham gia các phim trước của MCU, được trẻ hoá bằng kỹ xảo (do phim lấy bối cảnh năm 1995).[6][18][19]\n" +
                "\n" +
                "Đại uý Marvel khởi chiếu vào ngày 8 tháng 3 năm 2019 tại Mỹ,[20] trùng vào Ngày Quốc tế Phụ nữ,[21] dưới định dạng IMAX và 3D.[22][23] Bộ phim đã thu về 1,11 tỷ đô la Mỹ trên toàn thế giới, là phim có doanh thu cao thứ nhì năm 2019, cũng như phim siêu anh hùng có doanh thu cao thứ 9 mọi thời đại, và là phim có doanh thu cao thứ 30 mọi thời đại. Các nhà phê bình mô tả nó là \"giải trí, thú vị và hiểu biết\" và ca ngợi diễn xuất của Larson, Jackson và Mendelsohn. Phần tiếp theo của nó là The Marvels, sẽ được công chiếu tại các rạp vào 28 tháng 7, 2023."));
        recyclerDataArrayList.add(new DataModel("Iron Man", R.drawable.iron_man,"Iron Man (tên thật là Tony Stark) là một siêu anh hùng hư cấu xuất hiện trong truyện tranh của Mỹ được xuất bản bởi Marvel Comics, cũng như các phương tiện truyền thông liên quan. Nhân vật này đã được sáng tác bởi nhà văn - nhà biên tập Stan Lee, được phát triển bởi Larry Lieber, và được thiết kế bởi họa sĩ Don Heck và Jack Kirby. Nhân vật xuất hiện lần đầu tiên trong truyện tranh Tales of Suspense # 39 (tháng 3 năm 1963).\n" +
                "\n" +
                "Anh được miêu tả là một tỉ phú người Mỹ, một ông trùm ngành công nghiệp, một kĩ sư thiên tài, Tony bị chấn thương ngực nặng trong một vụ bắt cóc, bị những kẻ bắt cóc ép buộc chế tạo cho chúng một loại vũ khí hủy diệt hàng loạt. Nhưng thay vào đó, anh tự tay tạo ra một bộ áo giáp để cứu sống bản thân và thoát khỏi sự giam cầm. Lấy cảm hứng từ đó, anh nâng cấp bộ giáp mình đến một mức tối tân và phi thường hơn thông qua công ty của mình, Stark Industries. Anh sử dụng bộ giáp của mình để bảo vệ thế giới với tên gọi là Iron Man.\n" +
                "\n" +
                "Ban đầu, nhân vật này là phương tiện để Stan Lee đề cập đến chủ đề liên quan tới Chiến tranh Lạnh, đặc biệt là về vai trò của ngành công nghệ và kinh doanh của Mỹ trong cuộc chiến chống lại chủ nghĩa cộng sản. Sau đó tác giả lấy nhân vật để đề cập sang các vấn đề về các tổ chức tội phạm và khủng bố.\n" +
                "\n" +
                "Trong suốt chiều dài lịch sử xuất bản của nhân vật, Iron Man đã từng là một thành viên sáng lập của nhóm siêu anh hùng Avengers và xuất hiện trong các loạt truyện tranh khác của mình.\n" +
                "\n" +
                "Nhân vật được nhập vai bởi diễn viên Robert Downey Jr, đem lại một thành công rực rỡ cho phòng vé, góp phần mở ra hẳn một kỉ nguyên mới về đề tài siêu anh hùng trong điện ảnh Mỹ."));
        recyclerDataArrayList.add(new DataModel("Black Widow", R.drawable.black_widow,"Góa phụ đen (tên gốc tiếng Anh: Black Widow) là một bộ phim siêu anh hùng Mỹ ra mắt dựa trên nhân vật cùng tên của Marvel Comics do Marvel Studios sản xuất và Walt Disney Studios Motion Pictures phân phối. Goá phụ đen là bộ phim thứ 24 mở đầu cho Giai đoạn 4 trong Vũ trụ Điện ảnh Marvel (MCU). Bộ phim được Cate Shortland làm đạo diễn, do Eric Pearson viết kịch bản dựa trên câu chuyện của Jac Schaeffer và Ned Benson, và có sự tham gia của Scarlett Johansson trong vai Natasha Romanoff / Black Widow, cùng với David Harbor, Florence Pugh, O. T. Fagbenle, William Hurt, Ray Winstone và Rachel Weisz. Lấy bối cảnh ngay sau sự kiện Captain America: Nội chiến siêu anh hùng (2016), Romanoff đang tìm hiểu và đối mặt với quá khứ của mình.\n" +
                "\n" +
                "Việc phát triển phim Góa phụ đen bắt đầu vào tháng 4 năm 2004 bởi Lionsgate, với David Hayter viết và đạo diễn. Dự án đã không tiến triển và bản quyền phim cho nhân vật được trả lại cho Marvel Studios vào tháng 6 năm 2006. Johansson đã được chọn vào vai cho một số bộ phim lấy bối cảnh trong MCU, bắt đầu với Iron Man 2 (2010). Marvel và Johansson đã bày tỏ sự quan tâm đến một bộ phim solo tiềm năng nhiều lần trong những năm sau đó, trước khi sự phát triển tiến triển với việc thuê Schaeffer và Shortland vào năm 2018. Benson đã được thuê và tiếp tục casting vào đầu năm 2019. Việc quay phim diễn ra từ tháng 5 đến tháng 10 năm 2019 Na Uy, Budapest, Maroc, Pinewood Studios ở Anh, và tại Atlanta và Macon, Georgia.\n" +
                "\n" +
                "Góa phụ đen được phát hành tại Hoa Kỳ vào ngày 9 tháng 7 năm 2021 với vai trò là phim mở đầu cho Giai đoạn 4 của MCU. Lịch chiếu đã bị dời lại ba lần từ lịch chiếu ban đầu là tháng 5 năm 2020 do đại dịch COVID-19. Bộ phim cũng vướng vào vụ kiện lớn do Disney chiếu lên nền tảng trực tuyến (Disney+) khiến nữ diễn viên Scarlett Johansson đâm đơn kiện."));
        recyclerDataArrayList.add(new DataModel("Spider Man", R.drawable.spiderman,"Người nhện (tiếng Anh: Spider-Man) là một siêu anh hùng hư cấu trong các truyện tranh xuất bản bởi Marvel Comics. Nhân vật này được sáng tạo bởi nhà văn Stan Lee và nhà văn-nghệ sĩ Steve Ditko, lần đầu xuất hiện trong Amazing Fantasy #15 (tháng 8 năm 1962). Lee và Ditko tạo ra nhân vật là một đứa trẻ tuổi teen mồ côi, được dì May và dượng Ben nuôi nấng và phải đối phó với các cuộc thay đổi rất bình thường ở tuổi vị thành niên đồng thời cũng phải khoác lên một bộ trang phục khác để chống lại tội phạm. Những người tạo ra Người nhện đã cho cậu sức mạnh siêu nhiên và sự nhanh nhẹn, khả năng bám dính vào hầu hết các bề mặt, phun ra tơ từ cổ tay và phản ứng với các mối nguy hiểm một cách nhanh chóng nhờ có \"giác quan loài nhện\", giúp cậu chống lại kẻ thù của mình.\n" +
                "\n" +
                "Peter Parker là một cậu học sinh nhút nhát, được nuôi dưỡng bởi người dì và người chú giàu tình thương nhưng không có con, vậy nên họ luôn coi cậu như con ruột. Trong một lần đi tham dự buổi hội thảo khoa học, Peter vô tình bị cắn bởi một chú nhện trong phòng thí nghiệm. Sau khi bị hôn mê, khi tỉnh dậy cậu thấy mình trở nên vạm vỡ và có những khả năng phi thường. Cậu sử dụng khả năng của mình để thực hiện những việc tốt, giúp đỡ kẻ yếu nhưng luôn giữ kín danh phận của mình. Nhưng, có sức mạnh không có nghĩa là may mắn. Peter đã gặp phải một đối thủ hết sức lợi hại là Norman Osborn - một nhà khoa học bị gặp một vụ tai nạn đã biến hắn thành một kẻ nửa người máy độc ác, có sức mạnh không thua kém cậu.\n" +
                "\n" +
                "Khi Người Nhện lần đầu tiên ra mắt vào đầu những năm 1960, thanh thiếu niên trong các sách truyện tranh siêu anh hùng thường được chuyển xuống vai trò bạn thân của nhân vật chính. Loạt truyện Người Nhện đã tạo đột phá bằng nhân vật Peter Parker, một học sinh cấp 3 và đồng thời cũng là Người Nhện. Người Nhện là một trong những nhân vật siêu anh hùng phổ biến và thành công nhất về mặt thương mại[8], đồng thời cũng đã được chuyển thể thành phim. Tính đến tháng 2 năm 2015, đã có 5 tập phim về Người Nhện đã được ra mắt khán giả toàn cầu. Là một trong ba biểu tượng trong anh hùng truyện tranh của Mỹ với Người dơi và Siêu nhân."));
        recyclerDataArrayList.add(new DataModel("Thor", R.drawable.thor,"Thor (công chiếu ở Việt Nam với tên Thần Sấm) là một bộ phim nói về nhân vật siêu anh hùng cùng tên trong Marvel Comics. Được sản xuất bởi Marvel Studios và phân phối bởi Paramount Pictures, đây là bộ phim thứ 4 trong Vũ trụ Điện ảnh Marvel. Nó được đạo diễn bởi Kenneth Branagh, được viết bởi đội ngũ viết kịch bản của Ashley Edward Miller và Zack Stentz cùng với Don Payne, và Chris Hemsworth đóng vai nhân vật chính cùng với Natalie Portman, Tom Hiddleston, Stellan Skarsgård, Colm Feore, Ray Stevenson, Idris Elba, Kat Dennings, Rene Russo và Anthony Hopkins. Sau khi tham gia một cuộc chiến không cần thiết, Thor (Chris Hemsworth) bị trục xuất khỏi Asgard đến Trái đất, bị tước bỏ sức mạnh và chiếc búa Mjölnir. Khi em trai Loki (Tom Hiddleston) âm mưu chiếm ngai vàng Asgard, Thor phải chứng tỏ mình xứng đáng.\n" +
                "\n" +
                "Sam Raimi lần đầu tiên phát triển ý tưởng về một bộ phim chuyển thể dựa trên Thor vào năm 1991, nhưng ngay sau đó đã từ bỏ dự án, để nó trong \"địa ngục phát triển\" trong vài năm. Trong thời gian này, các hãng phim khác nhau đã chọn bản quyền cho đến khi Marvel ký hợp đồng với Mark Protosevich để phát triển dự án vào năm 2006, đồng thời lên kế hoạch tài trợ và phát hành nó thông qua Paramount. Matthew Vaughn được chỉ định làm đạo diễn cho bộ phim dự kiến \u200B\u200Bphát hành năm 2010. Tuy nhiên, sau khi Vaughn được giải phóng khỏi hợp đồng nắm giữ vào năm 2008, Branagh đã được tiếp cận và việc phát hành bộ phim được dời lại sang năm 2011. Các nhân vật chính được chọn vào năm 2009, và phần chụp ảnh chính diễn ra ở California và New Mexico từ tháng 1 đến tháng 5 năm 2010. Bộ phim đã được chuyển đổi sang 3D trong phần hậu kỳ.\n" +
                "\n" +
                "Thor được công chiếu lần đầu tại Sydney vào ngày 17 tháng 4 năm 2011, và được phát hành tại Hoa Kỳ vào ngày 6 tháng 5, như một phần của Giai đoạn Một của MCU. Bộ phim thành công về mặt tài chính, thu về 449,3 triệu đô la trên toàn thế giới. Các nhà phê bình khen ngợi màn trình diễn, nhân vật, chủ đề và hiệu ứng đặc biệt nhưng lại chỉ trích cốt truyện. Hai phần tiếp theo đã được phát hành: Thor: The Dark World (2013) và Thor: Ragnarok (2017). Phần phim thứ tư, Thor: Love and Thunder, dự kiến \u200B\u200Bphát hành vào tháng 7 năm 2022."));



        // added data from arraylist to adapter class.
        MovieListRecyclerAdapter adapter=new MovieListRecyclerAdapter(recyclerDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.movies_list);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, HomeActivity.class));
                return true;

            case R.id.account:
                startActivity(new Intent(this, AccountActivity.class));
                return true;

            case R.id.movies_list:
                return true;

            case R.id.contact_us:
                startActivity(new Intent(this, ContactUsActivity.class));
                return true;
        }
        return false;
    }


}