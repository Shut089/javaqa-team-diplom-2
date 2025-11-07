package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    /**
     * Тестирование пополнения кошелька
     * initialBalance - начальный баланс для счёта
     * creditLimit - максимальная сумма которую можно задолжать банку
     * rate - ставка кредитования для расчёта долга за отрицательный баланс
     */


    // Тестирование пополнения кошелька (Позитивное)
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    //Тестирование пополнения кошелька (Негативное). Добавляем отрицательное число должно быть 0
    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    // Тестирование пополнения кошелька. Добавляем число если на счете уже есть деньги
    @Test
    public void shouldAddToPositiveBalanceMoreThan0() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );
        account.add(2_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    // Тестирование пополнения кошелька. Добавляем дробное число в пустой кошелек
    @Test
    public void shouldAddToPositiveBalanceFractionalValuesNull() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(2_555);

        Assertions.assertEquals(2_555, account.getBalance());
    }

    // Тестирование пополнения кошелька. Добавляем дробное число в не пустой кошелек
    @Test
    public void shouldAddToPositiveBalanceFractionalValuesNotNull() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(2_555);

        Assertions.assertEquals(3_555, account.getBalance());
    }

    // Тестирование пополнения кошелька. Добавляем нулевое значение в кошелек
    @Test
    public void shouldAddToPositiveBalance0() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(0);

        Assertions.assertEquals(0, account.getBalance());
    }

    // Тестирование пополнения кошелька. Добавляем сразу несколько значений в кошелек
    @Test
    public void shouldAddToPositiveBalanceMany() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(1_000);
        account.add(2_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    // Тестирование пополнения кошелька. Проверка на сохранение старого баланса при неуспешной операции. Добавляем отрицательное значение
    @Test
    public void shouldAddToPositiveBalanceMinusSum() {
        CreditAccount account = new CreditAccount(
                100,
                5_000,
                15
        );
        account.add(-50);

        Assertions.assertEquals(100, account.getBalance());
    }

    // Тестирование пополнения кошелька. Пополнение большой суммой (проверка переполнения) - Тест не проходит
    @Test
    public void shouldAddOverflow() {
        CreditAccount account = new CreditAccount(
                Integer.MAX_VALUE - 10,
                5_000,
                15
        );
        account.add(100);

        Assertions.assertEquals(Integer.MAX_VALUE - 10, account.getBalance());
    }

    /**
     * Тестирование новый объект кредитного счёта
     * initialBalance - начальный баланс для счёта
     * creditLimit - максимальная сумма которую можно задолжать банку
     * rate - ставка кредитования для расчёта долга за отрицательный баланс
     */

    // Создание счета с валидными данными
    @Test
    public void testAddNewValid() {
        int initialBalance = 100;
        int creditLimit = 2_000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);


        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(rate, account.getRate());
        //Assertions.assertEquals(creditLimit, account.getcreditLimit());


    }

    // Создание счета с нулевыми значениями
    @Test
    public void testAddNewValidNull() {
        int initialBalance = 0;
        int creditLimit = 0;
        int rate = 0;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);


        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(rate, account.getRate());
        //Assertions.assertEquals(creditLimit, account.getcreditLimit());


    }

    // Создание счета с отрицательным значением rate
    @Test
    public void testAddNewInvalidRate() {
        int initialBalance = 100;
        int creditLimit = 2_000;
        int rate = -15; // Отрицательное значение

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new CreditAccount(initialBalance, creditLimit, rate);
                }
        );
    }

    // Создание счета с отрицательным значением initialBalance
    @Test
    public void testAddNewInvalidInitialBalance() {
        int initialBalance = -100; // Отрицательное значение
        int creditLimit = 2_000;
        int rate = 15;

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new CreditAccount(initialBalance, creditLimit, rate);
                }
        );
    }

    // Создание счета с отрицательным значением creditLimit
    @Test
    public void testAddNewInvalidCreditLimit() {
        int initialBalance = 100;
        int creditLimit = -2_000; // Отрицательное значение
        int rate = 15;

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new CreditAccount(initialBalance, creditLimit, rate);
                }
        );
    }

}
