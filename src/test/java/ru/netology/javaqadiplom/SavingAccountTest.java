package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // тесты для конструктора
    @Test // создание счёта с валидными параметрами
    public void shouldCreateAccountWithValidParams() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(5, account.getRate());
    }

    @Test // не создаёт счёт при отрицательном начальном балансе
    public void shouldThrowExceptionWithNegativeBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(-1, 1_000, 10_000, 5);
        });
    }

    @Test // не создаёт счёт при начальном балансе меньше минимального баланса
    public void shouldThrowExceptionWithBalanceLessThanMinBalace() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(999, 1_000, 10_000, 5);
        });
    }

    @Test // граничные значения: начальный баланс равен минимальному
    public void shouldCreateAccountWithBalanceEqualToMinBalance() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
    }

    @Test // граничные значения: начальный баланс равен (минимальный + 1)
    public void shouldCreateAccountWithBalanceEqualToMinBalancePlusOne() {
        SavingAccount account = new SavingAccount(
                1_001,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(1_001, account.getBalance());
        Assertions.assertEquals(1_000, account.getMinBalance());
    }

    @Test // не создаёт счёт при начальном балансе больше максимального баланса
    public void shouldThrowExceptionWithBalanceMoreThanMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10_001, 1_000, 10_000, 5);
        });
    }

    @Test // граничные значения: начальный баланс равен максимальному
    public void shouldCreateAccountWithBalanceEqualToMaxBalance() {
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(10_000, account.getBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
    }

    @Test // граничные значения: начальный баланс равен (максимальный - 1)
    public void shouldCreateAccountWithBalanceEqualToMaxBalanceMinusOne() {
        SavingAccount account = new SavingAccount(
                9_999,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(9_999, account.getBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
    }

    @Test // не создаёт счёт при отрицательном минимальном балансе
    public void shouldThrowExceptionWithNegativeMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, -1, 10_000, 5);
        });
    }

    @Test // не создаёт счёт, если минимальный баланс больше максимального
    public void shouldThrowExceptionWithMinBalanceMoreThanMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 10_001, 10_000, 5);
        });
    }

    @Test // создание счёта с равными минимальным и максимальным балансом, например, фиксированный вклад
    public void shouldCreateAccountWithMinBalanceEqualToMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                2_000,
                2_000,
                5
        );

        Assertions.assertEquals(2_000, account.getMinBalance());
        Assertions.assertEquals(2_000, account.getBalance());
        Assertions.assertEquals(2_000, account.getMaxBalance());
    }

    @Test // не создаёт счёт с отрицательной ставкой
    public void shouldThrowExceptionWithNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 1_000, 10_000, -1);
        });
    }

    // не пишу тест исключения с отрицательным максимальным балансом, так как такая проверка
    // должна покрываться тестами "минимальный баланс не должен быть отрицательным" и
    // "минимальный баланс не должен быть ниже максимального"

    // проверки метода pay
    @Test // платёж отрицательной суммы
    public void shouldNotPayNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(-1));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // платёж нулевой суммы
    public void shouldNotPayZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.pay(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // платёж валидной суммы
    public void shouldPayAnAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.pay(1));
        Assertions.assertEquals(2_000 - 1, account.getBalance());
    }

    @Test // платёж может опустить баланс ниже минимального
    public void shouldNotPayBelowMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(false, account.pay(1_001));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // платёж равняет баланс с минимальным
    public void shouldPayDownToMinBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(true, account.pay(1_000));
        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    // проверки метода add
    @Test // пополнение на отрицательную сумму
    public void shouldNotAddNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.add(-1));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // пополнение на нулевую сумму
    public void shouldNotAddZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.add(0));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test // пополнение счёта на сумму менее максимального баланса
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test // граничные значения: пополнение до (максимальный баланс -1)
    public void shouldAddUpToMaxBalanceMinusOne() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(7_999));
        Assertions.assertEquals(9_999, account.getBalance());
    }

    @Test // пополнение до максимального баланса
    public void shouldAddUpToMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(8_000));
        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test // пополнение выше максимального баланса
    public void shouldNotAddAboveMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(false, account.add(8_001));
        Assertions.assertEquals(2_000, account.getBalance());
    }

    // проверки метода yearChange
    @Test // расчёт процентов на сумму, кратную 100
    public void shouldCalculateChangeOnMultipleOf100Amount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(100, account.yearChange());
    }

    @Test // расчёт процентов на сумму, некратную 100
    public void shouldCalculateChangeOnNotMultipleOf100Amount() {
        SavingAccount account = new SavingAccount(
                2_099,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(104, account.yearChange());
    }
}
