<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('players', function (Blueprint $table) {
            $table->id();
            $table->string('member_code')->unique();
            $table->string('username');
            $table->string('avatar')->nullable();
            $table->date('birthday');
            $table->string('hometown');
            $table->string('residence');
            $table->decimal('rating_single', 5, 2)->default(0.00);
            $table->decimal('rating_double', 5, 2)->default(0.00);
            $table->timestamps();
        });
    
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('players');
    }
};
