package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        // get all categories
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
            }

            categories.add(mapRow(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getById(int categoryId) {
        // get category by id
        String sql = "SELECT * FROM categories WHERE category_id = ?";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }

            }


        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Category create(Category category) {
        // create a new category
        String sql = "INSERT INTO categories (name, description) VALUES (?,?)";

        try (var conn = getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.executeUpdate();
            {
                try (var keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                    category.setCategoryId(keys.getInt(1));
                    return category;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }


        @Override
        public void update(int categoryId, Category category) {
            String sql "UPDATE categories SET name = ?, description = ? WHERE category_id = ?"

            try (var conn = getConnection()
             var stmt = conn.prepareStatement(sql)) {
                // update category
                stmt.setString(1, category.getName());
                stmt.setString(2, category.getDescription());
                stmt.setInt(3, categoryId);
                stmt.executeUpdate();
            }
             catch(SQLException e) {
                e.printStackTrace();

            }


        }

        @Override
        public void delete ( int categoryId){
            // delete category
            String sql = "DELETE FROM categories WHERE category_id = ?";

            try (var conn = dataSource.getConnection()) ;
            var stmt = conn.prepareStatement(sql))
        }

        stmt.

                setInt(1, categoryId);
        stmt.

                executiveUpdate();
    }
        catch(
    SQLException e)

    {


        e.

                printStackTrace();
    }

    }

private Category mapRow(ResultSet row) throws SQLException {
    int categoryId = row.getInt("category_id");
    String name = row.getString("name");
    String description = row.getString("description");

    Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description);


    return category;
}
}


