

import com.user.domain.QueryVo;
import com.user.domain.User;
import java.util.List;

public interface UserDao {
    List<User> findAll() ;
    void saveUser(User user);
    int UserCount();
    List<User> findByVo(QueryVo queryVo);
}
