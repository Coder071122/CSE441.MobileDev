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
        Schema::create('game_matches', function (Blueprint $table) {
            $table->id();
            $table->string('match_code')->unique();
            $table->enum('type', ['single', 'double']);
            $table->string('player1')->nullable(); // Mã hội viên
            $table->string('player2')->nullable();
            $table->json('team1')->nullable(); // ["MBR001", "MBR002"]
            $table->json('team2')->nullable();
            $table->integer('score1')->nullable();
            $table->integer('score2')->nullable();
            $table->string('winner')->nullable();
            $table->enum('status', ['pending', 'completed'])->default('pending');
            $table->dateTime('datetime');
            $table->timestamps();
        });
    
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('game_matches');
    }
};
