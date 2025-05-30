<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Player extends Model
{
    protected $fillable = [
        'member_code', 'username', 'avatar', 'birthday',
        'hometown', 'residence', 'rating_single', 'rating_double'
    ];
    

    protected $casts = [
        'birthday' => 'date',
        'rating_single' => 'decimal:2',
        'rating_double' => 'decimal:2'
    ];

    public function transactions()
    {
        return $this->hasMany(Transaction::class, 'member_code', 'member_code');
    }

    public function singleMatches()
    {
        return $this->hasMany(GameMatch::class, 'player1', 'member_code')
            ->orWhere('player2', 'member_code');
    }
}
