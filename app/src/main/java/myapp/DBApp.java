package myapp;

import java.sql.Connection;
import java.util.List;

import myapp.config.DB;
import myapp.model.Product;
import myapp.model.ProductRepository2;
import myapp.service.ProductService;

public class DBApp {
    public static void main(String[] args) throws Exception{


        // 이러한 과정을 스프링이 컴포넌트 스캔으로 자동적으로 해줌
        // 1 커넥션 가져오기
        Connection conn = DB.getConnection();
        // 2 DAO 를 메모리에 올리기
        ProductRepository2 productRepository2 = new ProductRepository2(conn);
        // 3 SERVICE 를 메모리에 올리기
        ProductService productService = new ProductService(productRepository2, conn); // DBApp 은 컨트롤러의 역할을 한다 서비스를 의존한다
        // productService.상품등록("orange",2000,5);


        // Connection conn = DB.getConnection(); // 먼저 커넥션을 생성
        // ProductRepository2 productRepository = new ProductRepository2(conn);  // 이녀석은 커넥션을 가지고 있지 않아서 의존하는 커넥션을 주입해야함
        // productRepository.insert("apple",1000,30); // 쿼리가 작동한다 
        // // 이제 필요한 메소드들은 ProductRepository2 에 만들어야 한다.
        // // 여러 스레드를 사용한다면 트랜잭션처리가 필요한데 지금 위에꺼는 단순히 인서트만 해준다
        // // 트랜잭션은 서비스에서 처리해야하니 서비스를 만들자

        System.out.println(productRepository2.findById(2).getName());
        




        List<Product> pl = productRepository2.findAll();
        for (Product product : pl) {
            System.out.println("테스트 : "+product.getName());
        }
    }
}
