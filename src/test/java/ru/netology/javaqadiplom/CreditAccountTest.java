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

    // Тестирование пополнения кошелька. Добавляем произвольное число в пустой кошелек
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
        Assertions.assertEquals(creditLimit, account.getСreditLimit());


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

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти меньше чем лимит), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */

    //  Списание с положительного баланса
    @Test
    public void shouldCorrectlyReduceBalanceOnSuccessfulPayment() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int amount = 250;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int expectedBalance = initialBalance - amount; // 750

        boolean result = account.pay(amount);

        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    // --- Граничное условие: Обнуление ---
    @Test
    public void shouldPayDownToZero() {
        int initialBalance = 450;
        int creditLimit = 5000;
        int amount = 450;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int expectedBalance = 0;

        boolean result = account.pay(amount);

        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    // --- Уход в долг, но в пределах лимита ---
    @Test
    public void shouldSuccessfullyGoIntoDebtWithinLimit() {
        int initialBalance = 100;
        int creditLimit = 500; // Допустимый долг до -500
        int amount = 300; // Уходим в долг на -200

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int expectedBalance = -200;

        boolean result = account.pay(amount);

        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    // --- Граничное условие: Достижение максимального лимита ---
    @Test
    public void shouldSuccessfullyReachMaximumCreditLimitBoundary() {
        int initialBalance = 100;
        int creditLimit = 500;
        int amount = 600; // Уходим в долг точно до -500

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int expectedBalance = -creditLimit; // -500

        boolean result = account.pay(amount);

        Assertions.assertTrue(result);
        Assertions.assertEquals(expectedBalance, account.getBalance());
    }

    // Превышение кредитного лимита
    @Test
    public void shouldFailPaymentWhenExceedingCreditLimit() {
        int initialBalance = 100;
        int creditLimit = 500; // Допустимый долг до -500
        int paymentAmount = 601; // Попытка уйти в долг до -501 (нарушение лимита)

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int balanceBeforePayment = account.getBalance(); // 100

        boolean result = account.pay(paymentAmount);

        Assertions.assertFalse(result);
        Assertions.assertEquals(balanceBeforePayment, account.getBalance());
    }

    // --- Граничное условие: Превышение лимита на 1 ---
    @Test
    public void shouldFailPaymentWhenExceedingLimitByOne() {
        int initialBalance = 100;
        int creditLimit = 500;
        int paymentAmount = 601; // Нарушаем лимит на 1

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int balanceBeforePayment = account.getBalance(); // 100

        boolean result = account.pay(paymentAmount);

        Assertions.assertFalse(result);
        Assertions.assertEquals(balanceBeforePayment, account.getBalance());
    }

    // --- Невалидные входные данные: Отрицательная сумма ---
    @Test
    public void shouldFailPaymentWithNegativeAmount() {
        int initialBalance = 100;
        int creditLimit = 500;
        int negativeAmount = -50;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int balanceBeforePayment = account.getBalance(); // 100

        boolean result = account.pay(negativeAmount);

        Assertions.assertFalse(result);
        Assertions.assertEquals(balanceBeforePayment, account.getBalance());
    }

    // --- Невалидные входные данные: Нулевая сумма ---
    @Test
    public void shouldFailPaymentWithZeroAmount() {
        int initialBalance = 100;
        int creditLimit = 500;
        int zeroAmount = 0;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        int balanceBeforePayment = account.getBalance(); // 100

        boolean result = account.pay(zeroAmount);

        Assertions.assertFalse(result, "Метод должен вернуть false для нулевой суммы.");
        Assertions.assertEquals(balanceBeforePayment, account.getBalance(), "Баланс не должен измениться.");
    }

    /**
     * Операция расчёта процентов на отрицательный баланс счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте -200 рублей, то при ставке 15% ответ должен быть -30.
     * Пример 2: если на счёте 200 рублей, то при любой ставке ответ должен быть 0.
     *
     * @return
    */

    // Тест на положительный баланс — проценты не начисляются
    @Test
    public void shouldReturnZeroIfBalanceIsPositive() {
        CreditAccount account = new CreditAccount(500, 5000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    // Тест на нулевой баланс — тоже 0
    @Test
    public void shouldReturnZeroIfBalanceIsZero() {
        CreditAccount account = new CreditAccount(0, 5000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    // Тест на отрицательный баланс — стандартный пример
    @Test
    public void shouldCalculateInterestForNegativeBalance() {
        CreditAccount account = new CreditAccount(0, 5000, 15);
        account.pay(200); // Уводим в долг до -200

        int result = account.yearChange();

        Assertions.assertEquals(-30, result); // -200 / 100 * 15 = -30
    }

    // Тест: округление вниз (отсечение дробной части)
    @Test
    public void shouldTruncateFractionWhenDividing() {
        CreditAccount account = new CreditAccount(0, 5000, 15);
        account.pay(99); // Баланс: -99

        int result = account.yearChange();

        Assertions.assertEquals(0, result); // -99 / 100 = 0
    }

    // Тест: баланс ровно -100
    @Test
    public void shouldWorkExactlyAtMinus100() {
        CreditAccount account = new CreditAccount(0, 5000, 10);
        account.pay(100); // Баланс: -100

        int result = account.yearChange();

        Assertions.assertEquals(-10, result); // -100 / 100 * 10 = -10
    }

    // Тест с большой ставкой
    @Test
    public void shouldCalculateWithHighRate() {
        CreditAccount account = new CreditAccount(0, 5000, 50);
        account.pay(200); // Баланс: -200

        int result = account.yearChange();

        Assertions.assertEquals(-100, result); // -200 / 100 * 50 = -100
    }

    // Тест с глубоким долгом — округление
    @Test
    public void shouldTruncateWhenBalanceIsJustUnderMultiple() {
        CreditAccount account = new CreditAccount(0, 5000, 20);
        account.pay(299); // Баланс: -299

        int result = account.yearChange();

        Assertions.assertEquals(-40, result); // -299 / 100 = -2 → -2 * 20 = -40
    }
}
