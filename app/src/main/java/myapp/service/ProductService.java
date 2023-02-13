package myapp.service;

import java.sql.Connection;
import java.sql.SQLException;

import myapp.model.ProductRepository2;

public class ProductService {
    
    private ProductRepository2 productRepository2;
    private Connection conn; // 트랜잭션을 처리하기 위해 가져옴
    
    public ProductService(ProductRepository2 productRepository2, Connection conn) {
        this.productRepository2 = productRepository2;
        this.conn = conn; // 컨트롤러에서 만든 그 커넥셔을 가져옴
    }


    // 여러개의 트랜잭션을 처리해아함
    public void 상품등록(String name, int price, int qty) throws SQLException{
        try {
            conn.setAutoCommit(false);
            productRepository2.insert(name, price, qty);
            productRepository2.insert(name, price, qty);
            conn.commit(); // 메모리에 있는 인서트를 커넥션으로 커밋
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    public void 상품수정(int id, String name, int price, int qty) throws SQLException{
        try {
            productRepository2.updateById(id, name, price, qty);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
    public void 상품삭제(int id) throws SQLException{
        try {
            productRepository2.deleteById(id);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
