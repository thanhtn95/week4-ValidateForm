package userAccount.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import userAccount.model.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Integer> {
}
